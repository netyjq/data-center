package com.yjq.data.service.model;

import com.yjq.data.service.common.Constant;

import java.util.Date;

/**
 * 告警历史
 * 生成方式：通过轮询 api_invoke_record表 以及结合 alarm_config表 来生成告警记录添加到 alarm_history表
 * 分表规则：根据appId进行取模运算得到操作表
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class AlarmHistory {

    private Integer id;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 阈值
     */
    private Integer threshold;

    /**
     * 告警规则 1.慢查询 2.错误
     */
    private Integer alarmRule;

    /**
     * 实际数值
     */
    private Integer actualNum;

    /**
     * 告警内容
     */
    private String alarmMessage;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private Integer receiverId;

    /**
     * 用户名称
     */
    private String receiverName;

    /**
     * 表号
     */
    private Integer tableSuffix;

    public AlarmHistory(Integer appId, String appName, Integer threshold, Integer alarmRule, Integer actualNum,
                        String alarmMessage, Integer receiverId, String receiverName) {
        this.appId = appId;
        this.tableSuffix = appId % Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE;
        this.appName = appName;
        this.threshold = threshold;
        this.alarmRule = alarmRule;
        this.actualNum = actualNum;
        this.alarmMessage = alarmMessage;
        this.createTime = new Date();
        this.receiverId = receiverId;
        this.receiverName = receiverName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
        this.setTableSuffix(appId % Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getAlarmRule() {
        return alarmRule;
    }

    public void setAlarmRule(Integer alarmRule) {
        this.alarmRule = alarmRule;
    }

    public Integer getActualNum() {
        return actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getTableSuffix() {
        return tableSuffix;
    }

    public void setTableSuffix(Integer tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}

