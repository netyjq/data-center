package com.yjq.data.admin.mapper;

import com.yjq.data.admin.model.domain.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Mapper
public interface IUserGroupMapper extends IBaseMapper<UserGroup, Integer> {

    /**
     * 根据名称查询
     * @param name 用户组名称
     * @return UserGroup
     */
    UserGroup selectByName(@Param("name") String name);

}
