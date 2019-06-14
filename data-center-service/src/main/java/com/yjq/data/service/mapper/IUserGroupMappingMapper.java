package com.yjq.data.service.mapper;

import com.yjq.data.service.model.UserGroupMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@Mapper
public interface IUserGroupMappingMapper {

    /**
     * 根据组id查询
     * @param userGroupId 组id
     * @return List<UserGroupMapping>
     */
    List<UserGroupMapping> selectListByGroupId(@Param("userGroupId") Integer userGroupId);

}
