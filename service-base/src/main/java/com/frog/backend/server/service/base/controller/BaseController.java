package com.frog.backend.server.service.base.controller;

import cn.hutool.json.JSONUtil;
import com.frog.backend.server.service.core.constant.AuthConstant;
import com.frog.backend.server.service.core.pojo.oauth.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description Controller基类
 *
 * @author yxy
 * @date 2020-10-24
 */
public abstract class BaseController {

    /**
     * 获取当前请求HttpServletRequest对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前请求HttpServletResponse对象
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取当前登录用户，当前没有登录用户返回null
     *
     * @return
     */
    public UserDto getCurrentUser() {
        String userStr = getRequest().getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StringUtils.isBlank(userStr)) {
            return null;
        }
        return JSONUtil.toBean(userStr, UserDto.class);
    }
}
