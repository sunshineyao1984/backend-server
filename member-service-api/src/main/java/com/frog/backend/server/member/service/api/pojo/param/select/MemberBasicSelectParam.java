package com.frog.backend.server.member.service.api.pojo.param.select;

import com.frog.backend.server.service.base.pojo.param.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Description MemberBasicParam
 *
 * @author yxy
 * @date 2020/10/21
 */
@Data
public class MemberBasicSelectParam extends PageParam {

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "性别：0 男；1 女")
    private Boolean sex;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}