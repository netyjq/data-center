package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.GlobalStatusEnum;
import lombok.Data;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
@Data
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
}
