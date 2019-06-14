package com.yjq.data.admin.service;

import com.yjq.data.admin.mapper.IUserGroupMappingMapper;
import com.yjq.data.admin.model.domain.UserGroupMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-17
 */
@Service
public class UserGroupMappingService extends AbstractBaseService<UserGroupMapping, Integer> {

    @Autowired
    private IUserGroupMappingMapper userGroupMappingMapper;

    public List<UserGroupMapping> selectByUserId(Integer userId) {
        return userGroupMappingMapper.selectByUserId(userId);
    }

}
