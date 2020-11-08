package com.frog.backend.server.member.service.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-25
 */
@Data
public class SmsCodeParam {

    /**
     * 短信验证码类型： 0注册   1登录
     */
    @NotNull(message = "短信验证码类型不能为空！")
    protected Integer smsCodeType;

    @NotBlank(message = "手机号码不能为空白！")
    @NotNull(message = "手机号码不能为空！")
    protected String mobile;
}
