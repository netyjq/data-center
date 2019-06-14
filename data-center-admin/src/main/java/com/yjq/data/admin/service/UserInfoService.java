package com.yjq.data.admin.service;

import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.mapper.IUserInfoMapper;
import com.yjq.data.admin.model.domain.UserInfo;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class UserInfoService extends AbstractBaseService<UserInfo, Integer> {

    @Autowired
    private IUserInfoMapper userInfoMapper;

    public void insertUserInfo(String name) {
        UserInfo userInfo = findByName(name);
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setEmail(name+"@317hu.com");
            userInfo.setLastLogin(new Date());
            userInfo.setRoleType(0);
            userInfo.setName(name);
            userInfoMapper.insertOne(userInfo);
        } else {
            userInfo.setLastLogin(new Date());
            userInfoMapper.updateOne(userInfo);
        }
    }

    public UserInfo findByName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new ServiceException("name must not be null or empty");
        }
        return userInfoMapper.selectByName(name);
    }

    /**
     * 查询用户组成员
     * @param groupId 组id
     * @return List<UserInfo>
     */
    public List<UserInfo> findMembers(Integer groupId) {
        return userInfoMapper.selectByGroupId(groupId);
    }


}
