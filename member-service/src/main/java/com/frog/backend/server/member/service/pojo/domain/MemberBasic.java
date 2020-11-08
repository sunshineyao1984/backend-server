package com.frog.backend.server.member.service.pojo.domain;

import com.frog.backend.components.mysql.component.pojo.domain.BaseDomain;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * Description member_basic表DO
 * 
 * @author yxy
 * @date 2020/10/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_basic")
public class MemberBasic extends BaseDomain {
    /**
     * 会员ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别：0 男；1 女
     */
    private Boolean sex;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}