package com.yjq.data.service.model;

import lombok.Data;

/**
 * 用户、组之间的映射关系
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@Data
public class UserGroupMapping {

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户组id
     */
    private Integer userGroupId;

    public UserGroupMapping(Integer userId, Integer userGroupId) {
        this.userId = userId;
        this.userGroupId = userGroupId;
    }

    public UserGroupMapping() {
    }

}
