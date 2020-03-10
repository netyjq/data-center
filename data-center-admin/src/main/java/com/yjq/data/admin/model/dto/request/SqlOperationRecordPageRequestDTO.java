package com.yjq.data.admin.model.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-13
 */
@Data
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

    public void setPage(Integer page) {
        this.setPageNum(page);
        this.page = page;
    }

    public void setLimit(Integer limit) {
        this.setPageSize(limit);
        this.limit = limit;
    }
}
