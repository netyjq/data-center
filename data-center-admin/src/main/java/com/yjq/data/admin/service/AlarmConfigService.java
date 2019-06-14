package com.yjq.data.admin.service;

import com.yjq.data.admin.common.BeanUtil;
import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.mapper.IAlarmConfigMapper;
import com.yjq.data.admin.model.domain.AlarmConfig;
import com.yjq.data.admin.model.domain.AppInfo;
import com.yjq.data.admin.model.domain.UserGroup;
import com.yjq.data.admin.model.domain.UserInfo;
import com.yjq.data.admin.model.dto.request.AlarmConfigRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class AlarmConfigService extends AbstractBaseService<AlarmConfig, Integer> {

    @Autowired
    private IAlarmConfigMapper alarmConfigMapper;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private UserGroupService userGroupService;

    /**
     * 添加告警配置
     * @param insertDTO AlarmConfigRequestDTO
     * @param userInfo UserInfo
     */
    public void insertAlarmConfig(AlarmConfigRequestDTO insertDTO, UserInfo userInfo) {
        AppInfo appInfo = baseCheck(insertDTO);
        UserGroup userGroup = userGroupService.get(insertDTO.getUserGroupId());
        if (userGroup == null) {
            throw new ServiceException("无法查询到用户组。userGroupId: " + insertDTO.getUserGroupId());
        }
        AlarmConfig alarmConfig = new AlarmConfig(insertDTO.getAppId(), appInfo.getName(), userInfo.getId(),
                userInfo.getName(), null,null, insertDTO.getThreshold(), insertDTO.getRule(),
                insertDTO.getUserGroupId(), userGroup.getName());
        super.insert(alarmConfig);

    }


    /**
     * 更新告警配置
     * @param updateDTO AlarmConfigRequestDTO
     * @param userInfo UserInfo
     */
    public void updateAlarmConfig(AlarmConfigRequestDTO updateDTO, UserInfo userInfo) {
        baseCheck(updateDTO);
        AlarmConfig alarmConfig = super.get(updateDTO.getId());
        if (alarmConfig == null) {
            throw new ServiceException("无法查询到告警配置。id: " + updateDTO.getId());
        }
        alarmConfig.setAppId(updateDTO.getAppId());
        alarmConfig.setUpdateId(userInfo.getId());
        alarmConfig.setUpdateName(userInfo.getName());
        alarmConfig.setThreshold(updateDTO.getThreshold());
        alarmConfig.setRule(updateDTO.getRule());
        alarmConfig.setUserGroupId(updateDTO.getUserGroupId());
        super.update(alarmConfig);
    }

    /**
     * 根据appId搜索
     * @param appId 应用id
     * @return List<AlarmConfig>
     */
    public List<AlarmConfig> findAlarmConfigListByAppId(Integer appId) {
        return super.list(BeanUtil.getPropertyMap("appId", appId));
    }

    public void deleteAlarmConfig(Integer id) {
        AlarmConfig alarmConfig = alarmConfigMapper.selectOne(id);
        if (alarmConfig == null) {
            throw new ServiceException("删除失败，无法查询到该记录。id: " + id);
        }
        alarmConfigMapper.deleteOne(id);
    }


    private AppInfo baseCheck(AlarmConfigRequestDTO operateDTO) {
        if (!operateDTO.validate()) {
            throw new ServiceException("参数校验不通过");
        }
        Integer appId = operateDTO.getAppId();
        AppInfo appInfo = appInfoService.get(appId);
        if (appInfo == null) {
            throw new ServiceException("无法查询到应用。appId: " + appId);
        }
        return appInfo;
    }

}
