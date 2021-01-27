package com.frog.backend.server.system.service.pojo.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.frog.backend.components.sqlserver.component.util.UUIDGenId;
import com.frog.backend.server.service.base.pojo.domain.BaseDomain;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.code.ORDER;

/**
 * Description user_info表DO
 * 
 * @author yxy
 * @date 2021/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class UserInfo {
    /**
     */
    @Id
    @KeySql(genId = UUIDGenId.class)
    private String userId;

    /**
     */
    private String userName;
}