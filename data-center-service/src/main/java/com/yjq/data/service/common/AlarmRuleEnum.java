package com.yjq.data.service.common;

/**
 * 告警规则
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
public enum AlarmRuleEnum {

    /**
     * 慢查询数量
     */
    SLOW_COUNT(1, "慢查询数量"),

    /**
     * 错误数量
     */
    ERROR_COUNT(2, "错误数量");

    public static AlarmRuleEnum getByRuleCode(int ruleCode) {
        for (AlarmRuleEnum alarmRuleEnum : AlarmRuleEnum.values()) {
            if (alarmRuleEnum.getRuleCode() == ruleCode) {
                return alarmRuleEnum;
            }
        }
        return SLOW_COUNT;
    }

    private int ruleCode;

    private String desc;

    AlarmRuleEnum(int ruleCode, String desc) {
        this.ruleCode = ruleCode;
        this.desc = desc;
    }

    public int getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(int ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
