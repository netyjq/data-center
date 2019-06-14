package com.yjq.data.service.alarm;

import com.yjq.data.service.common.Constant;
import com.yjq.data.service.mapper.IAlarmHistoryMapper;
import com.yjq.data.service.mapper.IUserGroupMappingMapper;
import com.yjq.data.service.mapper.IUserInfoMapper;
import com.yjq.data.service.model.AlarmHistory;
import com.yjq.data.service.model.UserGroupMapping;
import com.yjq.data.service.model.UserInfo;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@Service
public class AlarmMessageSenderImpl implements AlarmMessageSender {

    @Autowired
    private MailSendHandler mailSendHandler;

    @Autowired
    private IUserGroupMappingMapper userGroupMappingMapper;

    @Autowired
    private IUserInfoMapper userInfoMapper;

    @Autowired
    private IAlarmHistoryMapper alarmHistoryMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(AlarmChecker alarmChecker) {
        List<UserGroupMapping> mappings = userGroupMappingMapper.selectListByGroupId(alarmChecker.getUserGroupId());
        if (mappings.isEmpty()) {
            logger.info(">>> 发送告警邮件失败,没有查询到用户组. groupId: {}", alarmChecker.getUserGroupId());
            return;
        }
        Set<Integer> userIdList = mappings.stream().map(UserGroupMapping::getUserId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = userInfoMapper.selectEmail(Joiner.on(",").join(userIdList));
        if (userInfoList.isEmpty()) {
            logger.info(">>> 发送告警邮件失败,没有查询到用户. userId: {}", Joiner.on(",").join(userIdList));
            return;
        }
        userInfoList.forEach(userInfo -> {
            boolean flag = mailSendHandler.send(userInfo.getEmail(), Constant.ALARM_EMAIL_SUBJECT, alarmChecker.getEmailMessage());
            if (flag) {
                AlarmHistory alarmHistory = new AlarmHistory(alarmChecker.getAppId(), alarmChecker.getAppName(),
                        alarmChecker.getThreshold(), alarmChecker.getRule().getRuleCode(), alarmChecker.getActualNum(),
                        alarmChecker.getEmailMessage(), userInfo.getId(), userInfo.getName());
                alarmHistoryMapper.insertOne(alarmHistory);
            }
        });
    }
}
