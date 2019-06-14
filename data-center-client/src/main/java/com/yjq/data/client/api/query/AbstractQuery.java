package com.yjq.data.client.api.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author netyjq@gmail.com
 * @date 2019/4/22
 */
public abstract class AbstractQuery implements Serializable {

    /**
     * application id
     */
    private Integer appId;

    /**
     * id of SqlTemplate
     */
    private String sqlId;

    /**
     * parameters
     */
    private Map<String, Object> filters;

    /**
     * 请求id
     */
    private String requestId;


    /**
     * validation
     * @return return true if parameters can't through validation, otherwise return false
     */
    public abstract boolean invalid();

    public AbstractQuery(Integer appId, String sqlId, Map<String, Object> filters) {
        this.appId = appId;
        this.sqlId = sqlId;
        this.filters = filters;
        this.requestId = UUID.randomUUID().toString();
    }

    public AbstractQuery() {
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public Map<String, Object> getFilters() {
        if (filters == null) {
            this.filters = new HashMap<String, Object>();
        }
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
