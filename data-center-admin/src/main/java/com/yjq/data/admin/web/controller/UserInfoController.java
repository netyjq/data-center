package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author netyjq@gmail.com
 * @date 2019-05-05
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController extends AbstractController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/list")
    public ResponseDTO list() {
        return new ResponseDTO(userInfoService.listAll());
    }

}

