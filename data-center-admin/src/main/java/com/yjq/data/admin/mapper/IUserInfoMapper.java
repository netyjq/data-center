package com.yjq.data.admin.mapper;

import com.yjq.data.admin.model.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Mapper
public interface IUserInfoMapper extends IBaseMapper<UserInfo, Integer> {

    /**
     * 根据登录账号名称查询
     * @param name 账号名称
     * @return UserInfo
     */
    UserInfo selectByName(String name);


    /**
     * 根据登录账号名称查询
     * @param userGroupId 组id
     * @return UserInfo
     */
    List<UserInfo> selectByGroupId(@Param("userGroupId") Integer userGroupId);

}
