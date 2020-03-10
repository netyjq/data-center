package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.dto.AbstractDTO;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-16
 */
@Data
public class UserGroupAppDTO extends AbstractDTO {

    @NotNull(groups = {ValidationMarker.InsertGroup.class, ValidationMarker.DeleteGroup.class})
    private Integer userGroupId;

    @NotEmpty(groups = {ValidationMarker.InsertGroup.class})
    private String appIds;

    @NotNull(groups = {ValidationMarker.DeleteGroup.class})
    private Integer appId;

    @Override
    public boolean validate() {
        return false;
    }
}
