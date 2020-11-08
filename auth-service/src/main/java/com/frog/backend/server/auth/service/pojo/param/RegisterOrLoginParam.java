package com.frog.backend.server.auth.service.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description 注册请求参数
 *
 * @author yxy
 * @date 2020-10-18
 */
@Data
public class RegisterOrLoginParam extends OAuth2Param {

    /**
     * 登录类型： 0注册， 1密码登录， 2短信验证码登录
     */
    @NotNull(message = "登录类型不能为空！")
    private Integer type;

    @NotBlank(message = "用户名不能为空白！")
    @NotNull(message = "用户名不能为空！")
    private String username;

    /**
     * 登录密码，密码登录（type=1）时必填
     */
    private String password;

    /**
     * 短信验证码，注册（type=0）或短信验证码登录（type=2）时必填
     */
    private String smsCode;

}
