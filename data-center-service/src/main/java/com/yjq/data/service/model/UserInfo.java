package com.yjq.data.service.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
@Data
public class UserInfo {

    private Integer id;

    /**
     * 姓名  邮箱前缀 like yejq
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

}
