package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

import java.util.Date;

/**
 * 用户与应用映射
 * @author netyjq@gmail.com
 * @date 2019-04-26
 */
@Data
public class AppUserGroupMapping extends AbstractModel<Integer> {

    private Integer id;

    /**
     * 应用ID
     */
    private Integer appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 用户id
     */
    private Integer userGroupId;

    /**
     * 用户名称
     */
    private String userGroupName;

    /**
     * 创建时间
     */
    private Date createTime;

    public AppUserGroupMapping(Integer appId, String appName, Integer userGroupId, String userGroupName) {
        this.appId = appId;
        this.appName = appName;
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
        this.createTime = new Date();
    }

    public AppUserGroupMapping() {
    }
}
