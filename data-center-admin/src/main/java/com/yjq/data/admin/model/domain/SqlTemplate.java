package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;

import java.util.Date;
import java.util.UUID;

/**
 * sql模板
 * @author netyjq@gmail.com
 * @date 2019/4/24
 */
public class SqlTemplate extends AbstractModel<String> {

    private String id;

    /**
     * sql模板
     */
    private String sqltext;

    /**
     * 描述
     */
    private String description;

    /**
     * 应用id
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 状态0 正常 -1 禁用
     */
    private Integer enableStatus;

    /**
     * 是否分页 0分页 -1不分页
     */
    private Integer paging;

    /**
     * 出参映射 like: {"app_id":"appId","enable_status":enableStatus}
     */
    private String parameterMapping;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人 equals User#getName
     */
    private String createName;

    /**
     * 创建人id
     */
    private Integer createId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateName;

    /**
     * 更新人id
     */
    private Integer updateId;

    public SqlTemplate(String sqltext, String description, Integer appId, String appName, Integer enableStatus,
                       Integer paging, String parameterMapping, String createName, Integer createId) {
        this.sqltext = sqltext;
        this.description = description;
        this.appId = appId;
        this.appName = appName;
        this.enableStatus = enableStatus;
        this.paging = paging;
        this.parameterMapping = parameterMapping;
        this.createTime = new Date();
        this.createName = createName;
        this.id = UUID.randomUUID().toString();
        this.createId = createId;
    }

    public SqlTemplate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(Integer paging) {
        this.paging = paging;
    }

    public String getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(String parameterMapping) {
        this.parameterMapping = parameterMapping;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }
}
