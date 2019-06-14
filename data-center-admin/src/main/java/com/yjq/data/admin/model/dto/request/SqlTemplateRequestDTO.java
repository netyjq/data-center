package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.GlobalStatusEnum;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
public class SqlTemplateRequestDTO extends PageRequestDTO {

    private Integer appId;

    private Integer enableStatus;

    private Integer paging;

    @Override
    public boolean validate() {
        if (paging != null) {
            if (paging < 0 || paging > 1) {
                return false;
            }
        }
        if (enableStatus != null && GlobalStatusEnum.getByStatusCode(enableStatus) == null) {
            return false;
        }
        return true;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
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
}
