package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.dto.AbstractDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-16
 */
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

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getAppIds() {
        return appIds;
    }

    public void setAppIds(String appIds) {
        this.appIds = appIds;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
