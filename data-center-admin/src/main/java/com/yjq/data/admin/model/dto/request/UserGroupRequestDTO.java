package com.yjq.data.admin.model.dto.request;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.dto.AbstractDTO;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
public class UserGroupRequestDTO extends AbstractDTO {

    @NotNull(groups = {ValidationMarker.UpdateGroup.class, ValidationMarker.SelectGroup.class, ValidationMarker.DeleteGroup.class})
    private Integer id;

    @NotEmpty(groups = {ValidationMarker.InsertGroup.class})
    @Length(max = 15, groups = {ValidationMarker.InsertGroup.class})
    private String name;

    @NotEmpty(groups = {ValidationMarker.InsertGroup.class})
    @Length(max = 20, groups = {ValidationMarker.InsertGroup.class})
    private String description;

    /**
     * 用户id
     */
    @NotEmpty(groups = {ValidationMarker.UpdateGroup.class})
    @Length(max = 70, groups = {ValidationMarker.UpdateGroup.class})
    private String userIds;

    @NotNull(groups = {ValidationMarker.DeleteGroup.class})
    private Integer userId;

    @Override
    public boolean validate() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
