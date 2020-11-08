package com.frog.backend.server.auth.service.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description  OAuth2.0 相关请求参数
 *
 * @author yxy
 * @date 2020-10-18
 */
@Data
public class OAuth2Param {

    /**
     * 授权类型:
     * authorization_code | password | client_credentials | implicit | refresh_token
     */
    @NotBlank(message = "授权类型不能为空白！")
    @NotNull(message = "授权类型不能为空！")
    protected String grant_type;

    /**
     * 客户端ID
     * admin-app | member-app | enterprise
     */
    @NotBlank(message = "客户端ID不能为空白！")
    @NotNull(message = "客户端ID不能为空！")
    protected String client_id;

    /**
     * 客户端秘钥
     */
    @NotBlank(message = "客户端秘钥不能为空白！")
    @NotNull(message = "客户端ID不能为空！")
    protected String client_secret;
}
