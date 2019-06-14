package com.yjq.data.admin.common;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
public enum RoleTypeEnum {

    /**
     * 普通用户
     */
    COMMON_USER(0),

    /**
     * 管理员
     */
    ADMIN(1);

    private int roleType;

    RoleTypeEnum(int roleType) {
        this.roleType = roleType;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }
}
