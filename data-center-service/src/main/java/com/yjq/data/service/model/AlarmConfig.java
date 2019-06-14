package com.yjq.data.service.model;

/**
 * 告警配置
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class AlarmConfig {

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
     * 创建人id
     */
    private Integer createId;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 更新人id
     */
    private Integer updateId;

    /**
     * 更新人姓名
     */
    private String updateName;

    /**
     * 阈值
     */
    private Integer threshold;

    /**
     * 告警规则 SLOW_COUNT(1), ERROR_COUNT(2);
     */
    private Integer rule;

    /**
     * 用户组id
     */
    private Integer userGroupId;

    /**
     * 用户组名称
     */
    private String userGroupName;


    public AlarmConfig(Integer appId, String appName, Integer createId, String createName, Integer updateId,
                       String updateName, Integer threshold, Integer rule, Integer userGroupId) {
        this.appId = appId;
        this.appName = appName;
        this.createId = createId;
        this.createName = createName;
        this.updateId = updateId;
        this.updateName = updateName;
        this.threshold = threshold;
        this.rule = rule;
        this.userGroupId = userGroupId;
    }

    public AlarmConfig() {
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
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}
