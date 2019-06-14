package com.yjq.data.service.alarm;

import com.yjq.data.service.common.*;
import com.yjq.data.service.mapper.IAlarmConfigMapper;
import com.yjq.data.service.mapper.IApiInvokeRecordMapper;
import com.yjq.data.service.model.AlarmConfig;
import com.yjq.data.service.model.ApiInvokeRecord;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
@Component
public class AlarmScheduleTask {

    @Autowired
    private IApiInvokeRecordMapper apiInvokeRecordMapper;

    @Autowired
    private IAlarmConfigMapper alarmConfigMapper;

    @Autowired
    private AlarmMessageSender alarmMessageSender;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 触发告警 发送邮件
     *
     * 方案：
     * step1: 查询已经配置过告警的应用，得到 appIdList
     * step2: 查询未轮询的调用记录，条件包含 appIdList
     * step3: 分析调用记录数据，符合条件则发送邮件提醒
     * step4: 标记此次所有的调用记录为已轮询
     * step5: 添加告警记录
     */
    @Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 40000)
    @Transactional(rollbackFor = Exception.class)
    public void run() {
        List<AlarmConfig> configs = alarmConfigMapper.selectList(Collections.emptyMap());
        if (configs.isEmpty()) {
            logger.info(">>> 当前系统内没有系统录入告警配置,告警轮询终止.");
            return;
        }
        Set<Integer> appIds = configs.stream().map(AlarmConfig::getAppId).collect(Collectors.toSet());
        List<ApiInvokeRecord> apiInvokeRecords = selectApiInvokeRecords(appIds);
        if (apiInvokeRecords == null || apiInvokeRecords.isEmpty()) {
            logger.info(">>> 当前时间内没有接口调用记录,告警轮询终止.");
            return;
        }
        configs.forEach(alarmConfig -> {
            Integer appId = alarmConfig.getAppId();
            List<ApiInvokeRecord> records = apiInvokeRecords.stream().filter(apiInvokeRecord ->
                    apiInvokeRecord.getAppId().equals(appId)).collect(Collectors.toList());
            analysis(alarmConfig, records);
        });

        // 设置标致位：已轮询处理
        apiInvokeRecords.forEach(apiInvokeRecord -> {
            apiInvokeRecord.setProcessed(ProgressEnum.PROCESSED.getCode());
            apiInvokeRecord.setProcessedTime(new Date());
            apiInvokeRecordMapper.updateOne(apiInvokeRecord);
        });
    }

    /**
     * 分析数据，发送告警邮件
     * @param alarmConfig 告警配置
     * @param records 请求流水
     */
    private void analysis(AlarmConfig alarmConfig, List<ApiInvokeRecord> records) {
        AlarmRuleEnum rule = AlarmRuleEnum.getByRuleCode(alarmConfig.getRule());
        final int originSize = records.size();
        if (rule == AlarmRuleEnum.ERROR_COUNT) {
            records = records.stream()
                    .filter(apiInvokeRecord -> apiInvokeRecord.getSuccess().equals(SuccessStatusEnum.FAILED.getCode()))
                    .collect(Collectors.toList());
            if (records.size() > alarmConfig.getThreshold()) {
                // 发送告警邮件
                alarmMessageSender.send(new AlarmChecker(alarmConfig, records.size()));
            }
        }
        if (rule == AlarmRuleEnum.SLOW_COUNT) {
            records = records.stream().filter(apiInvokeRecord -> apiInvokeRecord.getCost() > Constant.DEFAULT_TIMEOUT)
                    .collect(Collectors.toList());
            if (!records.isEmpty() && records.size() > alarmConfig.getThreshold()) {
                // 发送告警邮件
                alarmMessageSender.send(new AlarmChecker(alarmConfig, records.size()));
            }
        }
        logger.info(">>> 开始分析流水数据. 应用名称: {}, 用户组: {}, 告警类型: {}, 待分析数据条数: {}, 符合告警条数: {}",
                alarmConfig.getAppName(), alarmConfig.getUserGroupName(), rule.name(), originSize, records.size());
    }

    /**
     * 查询请求流水
     * @param appIds 应用id
     * @return List<ApiInvokeRecord>
     */
    private List<ApiInvokeRecord> selectApiInvokeRecords(Set<Integer> appIds) {
        List<ApiInvokeRecord> list = new ArrayList<>();
        Map<String, Object> filters = BeanUtil.getPropertyMap("processed,appIdListStr",
                ProgressEnum.UN_PROCESSED.getCode(), Joiner.on(",").join(appIds));
        for (int i = 0; i < Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE; i++) {
            filters.put("tableSuffix", i);
            List<ApiInvokeRecord> temp = apiInvokeRecordMapper.selectList(filters);
            list.addAll(temp);
        }
        return list;
    }

}
