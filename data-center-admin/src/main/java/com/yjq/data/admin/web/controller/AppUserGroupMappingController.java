package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.dto.request.UserGroupAppDTO;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.AppUserGroupMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-16
 */
@RestController
@RequestMapping("/app-user-group-mapping")
public class AppUserGroupMappingController {

    @Autowired
    private AppUserGroupMappingService appUserGroupMappingService;

    @RequestMapping("/insert")
    public ResponseDTO insertMapping(@Validated({ValidationMarker.InsertGroup.class}) UserGroupAppDTO userGroupAppDTO, BindingResult result) {
        appUserGroupMappingService.insertMapping(userGroupAppDTO.getAppIds(), userGroupAppDTO.getUserGroupId());
        return new ResponseDTO();
    }

    @RequestMapping("/delete")
    public ResponseDTO deleteMapping(@Validated({ValidationMarker.DeleteGroup.class}) UserGroupAppDTO userGroupAppDTO, BindingResult result) {
        appUserGroupMappingService.deleteMapping(userGroupAppDTO.getAppId(), userGroupAppDTO.getUserGroupId());
        return new ResponseDTO();
    }

}
