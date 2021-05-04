package com.frog.backend.server.system.service.service.impl;

import com.frog.backend.components.mysql.component.mapper.BaseMapper;
import com.frog.backend.components.mysql.component.service.impl.BaseServiceImpl;
import com.frog.backend.server.system.service.pojo.domain.SysUser;
import com.frog.backend.server.system.service.service.SysUserService;
import com.frog.backend.server.system.service.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Description SysUserServiceImpl
 *
 * @author yxy
 * @date 2021/05/04
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Long> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

    @Override
    protected BaseMapper<SysUser> getMapper() {
        return sysUserMapper;
    }

}