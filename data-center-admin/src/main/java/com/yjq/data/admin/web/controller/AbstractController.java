package com.yjq.data.admin.web.controller;

import com.yjq.data.admin.common.exception.SessionInvalidException;
import com.yjq.data.admin.model.domain.UserInfo;
import com.yjq.data.admin.service.UserInfoService;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author netyjq@gmail.com
 * @date 2019-04-29
 */
public abstract class AbstractController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 这里做自己的获取用户会话的逻辑
     * @return UserInfo
     */
    public UserInfo getLoginUser() {
//        String token = CookiesUtil.getCookieValue(request, "token");
//        if (Strings.isNullOrEmpty(token)) {
//            throw new SessionInvalidException("无会话，请重新登录");
//        }
        String name = "yejq";

        if (Strings.isNullOrEmpty(name)) {
            throw new SessionInvalidException("会话失效，请重新登录");
        }
        UserInfo userInfo = null;
        try {
            userInfo = userInfoService.findByName(name);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (userInfo == null) {
            throw new SessionInvalidException("账号不存在, name: " + name);
        }
        return userInfo;
    }


}
