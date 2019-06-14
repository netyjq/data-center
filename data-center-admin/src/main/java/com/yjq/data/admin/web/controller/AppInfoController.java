package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.BeanUtil;
import com.yjq.data.admin.common.exception.ParamInvalidException;
import com.yjq.data.admin.model.domain.AppInfo;
import com.yjq.data.admin.model.domain.AppUserGroupMapping;
import com.yjq.data.admin.model.dto.request.PageRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.AppInfoService;
import com.yjq.data.admin.service.AppUserGroupMappingService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-28
 */
@RestController
@RequestMapping("/appinfo")
public class AppInfoController extends AbstractController {

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private AppUserGroupMappingService appUserGroupMappingService;

    @RequestMapping("/insert")
    public ResponseDTO insert(String name) {
        if (Strings.isNullOrEmpty(name) || name.length() > 15) {
            throw new ParamInvalidException("name", "不能为空或者长度大于15个字符");
        }
        appInfoService.insertAppInfo(name);
        return new ResponseDTO();
    }

    @RequestMapping("/list")
    public ResponseDTO list() {
        List<AppInfo> list = appInfoService.list(getLoginUser());
        return new ResponseDTO(list);
    }

    @RequestMapping("/listAll")
    public ResponseDTO listAll() {
        List<AppInfo> list = appInfoService.listAll();
        return new ResponseDTO(list);
    }

    @RequestMapping("/layui/list")
    public LayuiResponseDTO list1(Integer userGroupId) {
        if (userGroupId == null) {
            return new LayuiResponseDTO(Collections.emptyList());
        }
        List<AppUserGroupMapping> list = appUserGroupMappingService.listByGroupId(userGroupId);
        if (list.isEmpty()) {
            return new LayuiResponseDTO(Collections.emptyList());
        }
        List<Integer> appIdList = list.stream().map(AppUserGroupMapping::getAppId).collect(Collectors.toList());
        List<AppInfo> appInfos = appInfoService.list(BeanUtil.getPropertyMap("appIdList", appIdList));
        return new LayuiResponseDTO(appInfos);
    }

    @RequestMapping("/page")
    public ResponseDTO list(PageRequestDTO pageRequestDTO) {
        PageInfo<AppInfo> list = appInfoService.listPage(pageRequestDTO);
        return new ResponseDTO(list);
    }
}

