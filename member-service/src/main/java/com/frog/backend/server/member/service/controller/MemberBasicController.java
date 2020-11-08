package com.frog.backend.server.member.service.controller;

import com.frog.backend.server.member.service.pojo.param.SmsCodeParam;
import com.frog.backend.server.member.service.service.MemberBasicService;
import com.frog.backend.server.service.base.controller.BaseController;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.constant.AuthConstant;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Map;


/**
 * Description MemberBasicController
 *
 * @author yxy
 * @date 2020/10/11
 */
@Slf4j
@RestController
public class MemberBasicController extends BaseController {

	@Autowired
	private MemberBasicService memberBasicService;

	/**
	 * 获取短信验证码
	 * @param smsCodeParam
	 * @return
	 */
	@PostMapping("/member/getSmsCode")
	public Result<Map<String,String>> getSmsCode(@Valid SmsCodeParam smsCodeParam){
		return memberBasicService.generateSmsCode(smsCodeParam);
	}

	@GetMapping("/member/getInfo")
	public Result<String> getUserInfo(){
		return ResultUtils.returnResultSuccess(getRequest().getHeader(AuthConstant.USER_TOKEN_HEADER));
	}

}