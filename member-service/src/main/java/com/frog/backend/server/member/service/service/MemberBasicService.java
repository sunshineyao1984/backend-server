package com.frog.backend.server.member.service.service;

import com.frog.backend.components.mysql.component.service.BaseService;
import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.member.service.pojo.domain.MemberBasic;
import com.frog.backend.server.member.service.pojo.param.SmsCodeParam;
import com.frog.backend.server.service.core.pojo.vo.Result;

import java.util.Map;

/**
 * Description MemberBasicService
 *
 * @author yxy
 * @date 2020/10/11
 */
public interface MemberBasicService extends BaseService<MemberBasic,Long> {

    /**
     * 会员注册
     * @param mobile
     * @param password
     * @param smsCode
     * @return
     */
    Result<MemberBasicVo> register(String mobile, String password, String smsCode);

    /**
     * 生成短信验证码
     * @param smsCodeParam
     * @return
     */
    Result<Map<String,String>> generateSmsCode(SmsCodeParam smsCodeParam);

}