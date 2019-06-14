package com.yjq.data.admin.service;

import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.mapper.IUserGroupMapper;
import com.yjq.data.admin.mapper.IUserGroupMappingMapper;
import com.yjq.data.admin.mapper.IUserInfoMapper;
import com.yjq.data.admin.model.domain.UserGroup;
import com.yjq.data.admin.model.domain.UserGroupMapping;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class UserGroupService extends AbstractBaseService<UserGroup, Integer> {

    @Autowired
    private IUserGroupMapper userGroupMapper;

    @Autowired
    private IUserGroupMappingMapper userGroupMappingMapper;

    @Autowired
    private IUserInfoMapper userInfoMapper;

    /**
     * 新增用户组
     * @param name 组名称
     * @param description 描述
     */
    public void insertUserGroup(String name, String description) {
        UserGroup userGroup = userGroupMapper.selectByName(name);
        if (userGroup != null) {
            throw new ServiceException("user group already exists");
        }
        userGroup = new UserGroup(name, description);
        super.insert(userGroup);
    }

    /**
     * 添加组成员
     * @param groupId 组id
     * @param userIds 用户id
     */
    public void addGroupMember(Integer groupId, String userIds) {
        UserGroup group = userGroupMapper.selectOne(groupId);
        if (group == null) {
            throw new ServiceException("group not found. id: " + group);
        }
        List<Integer> userIdList = Splitter.on(",").splitToList(userIds).stream().map(Integer::valueOf).collect(Collectors.toList());
        userIdList.forEach(userId-> {
            UserGroupMapping mapping = userGroupMappingMapper.select(userId, groupId);
            if (mapping == null) {
                mapping = new UserGroupMapping(userId, groupId);
                userGroupMappingMapper.insertOne(mapping);
            }
        });
    }

    /**
     * 删除组成员
     * @param groupId
     * @param userId
     */
    public void deleteMember(Integer groupId, Integer userId) {
        userGroupMappingMapper.deleteMapping(groupId, userId);
    }


}
