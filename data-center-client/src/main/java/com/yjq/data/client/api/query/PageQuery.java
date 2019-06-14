package com.yjq.data.client.api.query;


import java.util.Collections;
import java.util.Map;

/**
 * 分页query
 * @author netyjq@gmail.com
 * @date 2019/4/22
 */
public class PageQuery extends AbstractQuery {

    /**
     * 页码
     */
    private int pageNum;

    /**
     * size
     */
    private int pageSize;

    public PageQuery(Integer appId, String sqlId, Map<String, Object> filters, int pageNum, int pageSize) {
        super(appId, sqlId, filters);
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public PageQuery(Integer appId, String sqlId, int pageNum, int pageSize) {
        super(appId, sqlId, Collections.<String, Object>emptyMap());
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public PageQuery() {
    }

    @Override
    public boolean invalid() {
        if (super.getAppId() == null || super.getSqlId() == null || pageNum < 1 || pageSize < 1) {
            return true;
        }
        return false;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
