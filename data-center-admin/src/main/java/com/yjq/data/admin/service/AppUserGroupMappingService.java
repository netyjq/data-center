package com.yjq.data.admin.service;

import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.mapper.IAppUserGroupMappingMapper;
import com.yjq.data.admin.model.domain.*;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
@Service
public class AppUserGroupMappingService extends AbstractBaseService<AppUserGroupMapping, Integer> {

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private IAppUserGroupMappingMapper appUserGroupMappingMapper;

    @Autowired
    private UserGroupMappingService userGroupMappingService;

    public List<AppUserGroupMapping> list(UserInfo userInfo) {
        List<UserGroupMapping> userGroupMappings = userGroupMappingService.selectByUserId(userInfo.getId());
        if (userGroupMappings == null || userGroupMappings.isEmpty()) {
            return Collections.emptyList();
        }
        Set<Integer> groupIdSet = userGroupMappings.stream().map(UserGroupMapping::getUserGroupId).collect(Collectors.toSet());
        String groupIds = Joiner.on(",").join(groupIdSet);
        return appUserGroupMappingMapper.selectByGroupIds(groupIds);
    }

    /**
     * 根据userGroupId查询
     * @param userGroupId 用户组id
     * @return List<AppUserGroupMapping>
     */
    public List<AppUserGroupMapping> listByGroupId(Integer userGroupId) {
        return appUserGroupMappingMapper.selectByGroupIds(userGroupId.toString());
    }

    public void insertMapping(String appIds, Integer userGroupId) {
        List<Integer> appIdList = Splitter.on(",").splitToList(appIds).stream().map(Integer::valueOf).collect(Collectors.toList());
        appIdList.forEach(appId-> {
            AppInfo appInfo = appInfoService.get(appId);
            if (appInfo == null) {
                throw new ServiceException("未查询到应用, appId: " + appId);
            }
            UserGroup userGroup = userGroupService.get(userGroupId);
            if (userGroup == null) {
                throw new ServiceException("未查询到用户组, userGroupId: " + userGroupId);
            }
            AppUserGroupMapping appUserGroupMapping = appUserGroupMappingMapper.selectByAppIdAndGroupId(appId, userGroupId);
            if (appUserGroupMapping == null) {
                appUserGroupMapping = new AppUserGroupMapping(appId, appInfo.getName(), userGroupId, userGroup.getName());
                super.insert(appUserGroupMapping);
            }
        });
    }

    public void deleteMapping(Integer appId,Integer userGroupId) {
        appUserGroupMappingMapper.deleteMapping(appId, userGroupId);
    }


}
