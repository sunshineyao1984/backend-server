package com.frog.backend.server.system.service.controller;

import com.frog.backend.server.system.service.pojo.domain.SysUser;
import com.frog.backend.server.system.service.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * Description SysUserController
 *
 * @author yxy
 * @date 2021/05/04
 */
@Slf4j
@RestController
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

}