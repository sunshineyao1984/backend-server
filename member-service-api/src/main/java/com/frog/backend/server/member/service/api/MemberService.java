package com.frog.backend.server.member.service.api;

import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.service.core.pojo.vo.Result;

/**
 * Description 会员服务暴露Dubbo接口
 *
 * @author yxy
 * @date 2020-10-19
 */
public interface MemberService {

    /**
     * 会员注册
     * @param mobile
     * @param password
     * @param smsCode
     * @return
     */
    Result<MemberBasicVo> register(String mobile, String password,String smsCode);

    /**
     * 根据手机号码获取会员信息
     * @param mobile
     * @return
     */
    Result<MemberBasicVo> getMemberInfoByMobile(String mobile);

}
