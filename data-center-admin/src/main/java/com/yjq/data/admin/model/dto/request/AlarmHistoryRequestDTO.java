package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-06
 */
public class AlarmHistoryRequestDTO extends PageRequestDTO {

    /**
     * 数据库下标
     */
    private Integer tableSuffix;

    /**
     * 应用id
     */
    @NotNull
    private Integer appId;

    /**
     * 告警规则 1.慢查询 2.错误
     */
    private Integer alarmRule;

    @NotNull
    @Range(min = 1)
    private Integer page;

    @NotNull
    @Range(min = 1)
    private Integer limit;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
        this.tableSuffix = appId % Constant.ALARM_HISTORY_TABLE_SPLIT_SIZE;
    }

    public Integer getAlarmRule() {
        return alarmRule;
    }

    public void setAlarmRule(Integer alarmRule) {
        this.alarmRule = alarmRule;
    }

    public Integer getTableSuffix() {
        return tableSuffix;
    }

    public void setTableSuffix(Integer tableSuffix) {
        this.tableSuffix = tableSuffix;
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
