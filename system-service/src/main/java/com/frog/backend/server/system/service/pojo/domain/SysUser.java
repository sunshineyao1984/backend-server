package com.frog.backend.server.system.service.pojo.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.frog.backend.server.service.base.pojo.domain.BaseDomain;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.Version;

/**
 * Description sys_user表DO
 * 
 * @author yxy
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_user")
public class SysUser extends BaseDomain {
    /**
     * 用户ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 机构ID
     */
    private Long orgId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Boolean disableStatus;

    /**
     * 是否已删除（0未删除 1已删除）
     */
    private Boolean isDeleted;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新者
     */
    private String updater;
}