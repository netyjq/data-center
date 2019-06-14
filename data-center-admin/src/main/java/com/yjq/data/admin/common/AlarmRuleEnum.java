package com.yjq.data.admin.common;

/**
 * 告警规则
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
public enum AlarmRuleEnum {

    /**
     * 慢查询数量
     */
    SLOW_COUNT(1),

    /**
     * 错误数量
     */
    ERROR_COUNT(2);

    public static AlarmRuleEnum getByRuleCode(int code) {
        for (AlarmRuleEnum alarmRuleEnum : AlarmRuleEnum.values()) {
            if (alarmRuleEnum.getRuleCode() == code) {
                return alarmRuleEnum;
            }
        }
        return null;
    }

    private int ruleCode;

    AlarmRuleEnum(int ruleCode) {
        this.ruleCode = ruleCode;
    }

    public int getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(int ruleCode) {
        this.ruleCode = ruleCode;
    }
}
