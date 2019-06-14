package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;

import java.util.Date;

/**
 * 用户与应用映射
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class AppUserGroupMapping extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 应用ID
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 用户id
     */
    private Integer userGroupId;

    /**
     * 用户名称
     */
    private String userGroupName;

    /**
     * 创建时间
     */
    private Date createTime;

    public AppUserGroupMapping(Integer appId, String appName, Integer userGroupId, String userGroupName) {
        this.appId = appId;
        this.appName = appName;
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
        this.createTime = new Date();
    }

    public AppUserGroupMapping() {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
