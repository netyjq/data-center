package com.yjq.data.admin.mapper;

import com.yjq.data.admin.model.domain.AppUserGroupMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Mapper
public interface IAppUserGroupMappingMapper extends IBaseMapper<AppUserGroupMapping, Integer> {

    /**
     * 根据appId和userGroupId查询单个
     * @param appId 应用id
     * @param userGroupId groupId
     * @return AppUserGroupMapping
     */
    AppUserGroupMapping selectByAppIdAndGroupId(@Param("appId") Integer appId, @Param("userGroupId") Integer userGroupId);


    /**
     * 根据用户组id查询
     * @param groupIds "1,2,3"
     * @return List<AppUserGroupMapping>
     */
    List<AppUserGroupMapping> selectByGroupIds(@Param("groupIds") String groupIds);

    /**
     * 根据应用id查询
     * @param appIds "1,2,3"
     * @return List<AppUserGroupMapping>
     */
    List<AppUserGroupMapping> selectByAppIds(@Param("appIds") String appIds);

    /**
     * 删除关联
     * @param appId
     * @param userGroupId
     */
    void deleteMapping(@Param("appId") Integer appId, @Param("userGroupId") Integer userGroupId);

}
