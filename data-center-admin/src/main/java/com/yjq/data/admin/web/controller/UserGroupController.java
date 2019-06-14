package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.validation.ValidationMarker;
import com.yjq.data.admin.model.domain.UserGroup;
import com.yjq.data.admin.model.domain.UserInfo;
import com.yjq.data.admin.model.dto.request.UserGroupRequestDTO;
import com.yjq.data.admin.model.dto.response.LayuiResponseDTO;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.UserGroupService;
import com.yjq.data.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@RestController
@RequestMapping("user/group")
public class UserGroupController extends AbstractController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/insert")
    public ResponseDTO insert(@Validated({ValidationMarker.InsertGroup.class})UserGroupRequestDTO requestDTO,
                              BindingResult result) {
        userGroupService.insertUserGroup(requestDTO.getName(), requestDTO.getDescription());
        return new ResponseDTO();
    }

    @RequestMapping("/add-member")
    public ResponseDTO addMember(@Validated({ValidationMarker.UpdateGroup.class})UserGroupRequestDTO requestDTO,
                              BindingResult result) {
        userGroupService.addGroupMember(requestDTO.getId(), requestDTO.getUserIds());
        return new ResponseDTO();
    }

    @RequestMapping("/delete-member")
    public ResponseDTO deleteMember(@Validated({ValidationMarker.DeleteGroup.class})UserGroupRequestDTO requestDTO,
                                 BindingResult result) {
        userGroupService.deleteMember(requestDTO.getId(), requestDTO.getUserId());
        return new ResponseDTO();
    }

    @RequestMapping("/list")
    public LayuiResponseDTO list() {
        List<UserGroup> groups = userGroupService.listAll();
        LayuiResponseDTO responseDTO = new LayuiResponseDTO(groups);
        return responseDTO;
    }

    @RequestMapping("/listAll")
    public ResponseDTO listAll() {
        List<UserGroup> groups = userGroupService.listAll();
        return new ResponseDTO(groups);
    }

    @RequestMapping("/members")
    public LayuiResponseDTO findMembers(@Validated({ValidationMarker.SelectGroup.class})UserGroupRequestDTO requestDTO,
                                 BindingResult result) {
        List<UserInfo> userInfoList = userInfoService.findMembers(requestDTO.getId());
        LayuiResponseDTO responseDTO = new LayuiResponseDTO(userInfoList);
        return responseDTO;
    }

}
