package com.frog.backend.server.member.service.api.pojo.param.insert;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Description 会员注册请求参数
 *
 * @author yxy
 * @date 2020/10/11
 */
@Data
public class MemberRegisterParam implements Serializable {

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "登录密码")
    private String password;

}