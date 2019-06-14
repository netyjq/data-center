package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;

import java.util.Date;

/**
 * 用户信息
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
public class UserInfo extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 姓名 邮箱前缀 like yejq
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 最后一次登录时间
     */
    private Date lastLogin;

    /**
     * 用户组id
     */
    private Integer userGroupId;

    /**
     * 角色权限  0 普通用户  1 管理员用户
     */
    private Integer roleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }


}
