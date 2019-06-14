package com.yjq.data.admin.web.controller;

import com.google.common.base.Strings;
import com.yjq.data.admin.common.MD5;
import com.yjq.data.admin.common.exception.ParamInvalidException;
import com.yjq.data.admin.common.exception.ServiceException;
import com.yjq.data.admin.model.domain.UserInfo;
import com.yjq.data.admin.model.dto.response.ResponseDTO;
import com.yjq.data.admin.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author netyjq@gmail.com
 * @date 2018/9/19
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录检查
     * @return
     */
    @RequestMapping("/checkLogin")
    public ResponseDTO checkLogin(String uid, String password, HttpServletResponse response) {
        if (Strings.isNullOrEmpty(uid)) {
            throw new ParamInvalidException("username", "不能为空");
        }
        UserInfo userInfo = null;
        boolean flag = false;
        try {
            userInfo = userInfoService.findByName(uid);
            if (userInfo != null) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            String token = uid + ":" + MD5.sign(UUID.randomUUID().toString());
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(2 * 60 * 60 * 1000);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new ResponseDTO();
        }
        throw new ServiceException("登录失败，账号或密码错误");
    }

}
