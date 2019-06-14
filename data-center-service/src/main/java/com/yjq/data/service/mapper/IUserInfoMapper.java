package com.yjq.data.service.mapper;

import com.yjq.data.service.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Mapper
public interface IUserInfoMapper {

    /**
     * 根据用户id查询用户电子邮件地址
     * @param userIdListStr 用户列表
     * @return List<String>
     */
    List<UserInfo> selectEmail(@Param("userIdListStr") String userIdListStr);


}
