package com.yjq.data.service.alarm;

import com.yjq.data.service.common.AlarmRuleEnum;
import com.yjq.data.service.model.AlarmConfig;

/**
 * 告警检查器
 * @author netyjq@gmail.com
 * @date 2019-05-07
 */
public class AlarmChecker {

    /**
     * 用户组id
     */
    private Integer userGroupId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 阈值
     */
    private Integer threshold;

    /**
     * 实际值
     */
    private Integer actualNum;

    /**
     * 告警规则
     */
    private AlarmRuleEnum rule;

    public AlarmChecker(Integer userGroupId, String appName, Integer appId, Integer threshold, Integer actualNum, AlarmRuleEnum rule) {
        this.userGroupId = userGroupId;
        this.appName = appName;
        this.appId = appId;
        this.threshold = threshold;
        this.actualNum = actualNum;
        this.rule = rule;
    }

    public AlarmChecker(AlarmConfig alarmConfig, Integer actualNum) {
        this.userGroupId = alarmConfig.getUserGroupId();
        this.appName = alarmConfig.getAppName();
        this.appId = alarmConfig.getAppId();
        this.threshold = alarmConfig.getThreshold();
        this.actualNum = actualNum;
        this.rule = AlarmRuleEnum.getByRuleCode(alarmConfig.getRule());
    }

    public AlarmChecker() {
    }

    public String getEmailMessage() {
        return String.format("【%s】在过去的5分钟内，%s：%s，设定的阈值为：%s", appName, rule.getDesc(), actualNum, threshold);
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getActualNum() {
        return actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }

    public AlarmRuleEnum getRule() {
        return rule;
    }

    public void setRule(AlarmRuleEnum rule) {
        this.rule = rule;
    }

}
