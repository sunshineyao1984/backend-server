package com.frog.backend.server.auth.service.controller;

import com.frog.backend.server.auth.service.constant.RegisterOrLoginType;
import com.frog.backend.server.auth.service.pojo.param.RegisterOrLoginParam;
import com.frog.backend.server.auth.service.service.AuthService;
import com.frog.backend.server.service.base.controller.BaseController;
import com.frog.backend.server.service.core.pojo.oauth.Oauth2TokenDto;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-17
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    /**
     * 注册或登录
     *
     * @param principal
     * @param registerOrLoginParam
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/registerOrLogin")
    public Result<Oauth2TokenDto> registerOrLogin(@ApiIgnore Principal principal, @Valid RegisterOrLoginParam registerOrLoginParam)
            throws HttpRequestMethodNotSupportedException {
        //注册或短信验证码登录时，smsCode字段不能为空
        boolean isRegisterOrLoginBySmsCode = registerOrLoginParam.getType().equals(RegisterOrLoginType.REGISTER) ||
                registerOrLoginParam.getType().equals(RegisterOrLoginType.LOGIN_BY_SMS_CODE);
        if (isRegisterOrLoginBySmsCode && StringUtils.isBlank(registerOrLoginParam.getSmsCode())) {
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR, "验证码未填！", null);
        }
        //密码登录时，password字段不能为空
        if (registerOrLoginParam.getType().equals(RegisterOrLoginType.LOGIN_BY_PASSWORD) &&
                StringUtils.isBlank(registerOrLoginParam.getPassword())) {
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR, "密码未填！", null);
        }
        return authService.registerOrLogin(principal, registerOrLoginParam);
    }

}
