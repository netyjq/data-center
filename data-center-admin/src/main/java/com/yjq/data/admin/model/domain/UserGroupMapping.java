package com.yjq.data.admin.model.domain;

import com.yjq.data.admin.model.AbstractModel;
import lombok.Data;

/**
 * 用户、组之间的映射关系
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@Data
public class UserGroupMapping extends AbstractModel<Integer> {

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
