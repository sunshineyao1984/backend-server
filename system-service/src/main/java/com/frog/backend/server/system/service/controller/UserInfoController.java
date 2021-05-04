package com.frog.backend.server.system.service.controller;

import com.frog.backend.server.system.service.pojo.domain.UserInfo;
import com.frog.backend.server.system.service.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

/**
 * Description UserInfoController
 *
 * @author yxy
 * @date 2021/02/02
 */
@Slf4j
@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

}