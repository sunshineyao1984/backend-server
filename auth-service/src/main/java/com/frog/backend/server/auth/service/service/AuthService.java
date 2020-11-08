package com.frog.backend.server.auth.service.service;

import com.frog.backend.server.auth.service.pojo.param.RegisterOrLoginParam;
import com.frog.backend.server.service.core.pojo.oauth.Oauth2TokenDto;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-24
 */
public interface AuthService {

    /**
     * 注册或登录
     * @param principal
     * @param registerOrLoginParam
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    Result<Oauth2TokenDto> registerOrLogin(Principal principal, RegisterOrLoginParam registerOrLoginParam) throws HttpRequestMethodNotSupportedException;


}
