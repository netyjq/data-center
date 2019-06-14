package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;

import java.util.Date;

/**
 * sql模板操作记录
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class SqlOperationRecord extends AbstractModel<Integer> {

    private Integer id;

    /**
     * sql id
     */
    private String sqlId;

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改前sql
     */
    private String sqltextBefore;

    /**
     * 修改后sql
     */
    private String sqltextAfter;

    /**
     * 修改前分页情况
     */
    private Integer pageBefore;

    /**
     * 修改后分页情况
     */
    private Integer pageAfter;

    /**
     * 修改前表字段映射情况
     */
    private String parameterMappingBefore;

    /**
     * 修改后表字段映射情况
     */
    private String parameterMappingAfter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSqltextBefore() {
        return sqltextBefore;
    }

    public void setSqltextBefore(String sqltextBefore) {
        this.sqltextBefore = sqltextBefore;
    }

    public String getSqltextAfter() {
        return sqltextAfter;
    }

    public void setSqltextAfter(String sqltextAfter) {
        this.sqltextAfter = sqltextAfter;
    }

    public Integer getPageBefore() {
        return pageBefore;
    }

    public void setPageBefore(Integer pageBefore) {
        this.pageBefore = pageBefore;
    }

    public Integer getPageAfter() {
        return pageAfter;
    }

    public void setPageAfter(Integer pageAfter) {
        this.pageAfter = pageAfter;
    }

    public String getParameterMappingBefore() {
        return parameterMappingBefore;
    }

    public void setParameterMappingBefore(String parameterMappingBefore) {
        this.parameterMappingBefore = parameterMappingBefore;
    }

    public String getParameterMappingAfter() {
        return parameterMappingAfter;
    }

    public void setParameterMappingAfter(String parameterMappingAfter) {
        this.parameterMappingAfter = parameterMappingAfter;
    }
}
