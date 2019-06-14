package com.yjq.data.service.model;

/**
 * 用户、组之间的映射关系
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }
}
