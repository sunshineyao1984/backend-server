package com.frog.backend.server.system.service.mapper;

import com.frog.backend.components.mysql.component.mapper.BaseMapper;
import com.frog.backend.server.system.service.pojo.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * Description sys_user表Mapper
 * 
 * @author yxy
 * @date 2021/05/04
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
}