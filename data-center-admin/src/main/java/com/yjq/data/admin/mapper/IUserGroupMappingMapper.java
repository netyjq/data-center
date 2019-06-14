package com.yjq.data.admin.mapper;

import com.yjq.data.admin.model.domain.UserGroupMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@Mapper
public interface IUserGroupMappingMapper extends IBaseMapper<UserGroupMapping, Integer> {

    /**
     * 根据用户id、组id查询
     * @param userId 用户id
     * @param userGroupId 组id
     * @return
     */
    UserGroupMapping select(@Param("userId") Integer userId, @Param("userGroupId") Integer userGroupId);

    /**
     * 删除关联关系
     * @param userId 用户id
     * @param userGroupId 组id
     */
    void deleteMapping(@Param("userGroupId") Integer userGroupId, @Param("userId") Integer userId);

    /**
     * 根据用户id查询
     * @param userId 用户id
     * @return List<UserGroupMapping>
     */
    List<UserGroupMapping> selectByUserId(Integer userId);
}
