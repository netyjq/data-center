package com.yjq.data.admin.model.dto.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
public class SqlOperationRecordPageRequestDTO extends PageRequestDTO {

    /**
     * sql id
     */
    @NotNull
    private String sqlId;

    @NotNull
    @Range(min = 1)
    private Integer page;

    @NotNull
    @Range(min = 1)
    private Integer limit;

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
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
}
