package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.Constant;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
public class ApiInvokeRecordPageRequestDTO extends PageRequestDTO {

    /**
     * sql id
     */
    @NotNull
    private Integer appId;

    @NotNull
    @Range(min = 1)
    private Integer page;

    @NotNull
    @Range(min = 1)
    private Integer limit;

    /**
     * 数据库表号
     */
    private Integer tableSuffix;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.tableSuffix = appId % Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE;
        this.appId = appId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.setPageNum(page);
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.setPageSize(limit);
        this.limit = limit;
    }

    public Integer getTableSuffix() {
        return tableSuffix;
    }

    public void setTableSuffix(Integer tableSuffix) {
        this.tableSuffix = tableSuffix;
    }
}
