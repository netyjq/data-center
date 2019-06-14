package com.yjq.data.service.model;

import com.yjq.data.service.common.Constant;

import java.util.Date;

/**
 * 接口调用记录
 * 告警触发的规则就是定时轮训调用记录，判断是否符合告警的配置
 * 轮询完成后会设置标志位字段processed为-1，并记录处理时间
 *
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public class ApiInvokeRecord {

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
     * sql模板id
     */
    private String sqlId;

    /**
     * sql模板
     */
    private String sqltext;

    /**
     * 请求入参
     */
    private String requestParam;

    /**
     * 接收到请求的时的系统时间
     */
    private Date requestTime;

    /**
     * 请求响应时系统时间
     */
    private Date responseTime;

    /**
     * 消耗的时间
     */
    private Long cost;

    /**
     * 是否调用成功 0 成功 -1 失败
     */
    private Integer success;

    /**
     * 是否已经轮询处理过了 0未处理 -1已经处理
     */
    private Integer processed;

    /**
     * 轮询标记的时间
     */
    private Date processedTime;

    /**
     * 错误详情 执行出错时记录
     */
    private String errorDetail;

    /**
     * 表下标 只做查询用
     */
    private Integer tableSuffix;

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
        this.tableSuffix = appId % Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public Date getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(Date processedTime) {
        this.processedTime = processedTime;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public Integer getTableSuffix() {
        return tableSuffix;
    }

    public void setTableSuffix(Integer tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}
