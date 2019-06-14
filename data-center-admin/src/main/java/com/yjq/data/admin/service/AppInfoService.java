package com.yjq.data.admin.service;

import com.yjq.data.admin.common.BeanUtil;
import com.yjq.data.admin.mapper.IUserInfoMapper;
import com.yjq.data.admin.model.domain.AppInfo;
import com.yjq.data.admin.model.domain.AppUserGroupMapping;
import com.yjq.data.admin.model.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@Service
public class AppInfoService extends AbstractBaseService<AppInfo, Integer> {

    @Autowired
    private IUserInfoMapper userInfoMapper;

    @Autowired
    private AppUserGroupMappingService appUserGroupMappingService;

    public void insertAppInfo(String name) {
        AppInfo appInfo = new AppInfo(name);
        super.insert(appInfo);
    }


    public List<AppInfo> list(UserInfo userInfo) {
        List<AppUserGroupMapping> appUserGroupMappings = appUserGroupMappingService.list(userInfo);
        if (appUserGroupMappings == null || appUserGroupMappings.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, Object> params = BeanUtil.getPropertyMap("appIdList", appUserGroupMappings
                .stream()
                .map(AppUserGroupMapping::getAppId)
                .collect(Collectors.toList()));
        return super.list(params);
    }
}
