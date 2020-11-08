package com.frog.backend.server.service.core.pojo.oauth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDto {

    @ApiModelProperty("访问令牌")
    private String token;

    @ApiModelProperty("刷令牌")
    private String refreshToken;

    @ApiModelProperty("访问令牌前缀")
    private String tokenHead;

    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
