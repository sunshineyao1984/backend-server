package com.frog.backend.server.member.service.api.impl;

import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.member.service.pojo.domain.MemberBasic;
import com.frog.backend.server.member.service.service.MemberBasicService;
import com.frog.backend.server.member.service.api.MemberService;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Description
 *
 * @author yxy
 * @date 2020-10-19
 */
@DubboService(protocol = "dubbo")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberBasicService memberBasicService;

    @Override
    public Result<MemberBasicVo> register(String mobile, String password, String smsCode) {
        return memberBasicService.register(mobile,password,smsCode);
    }

    @Override
    public Result<MemberBasicVo> getMemberInfoByMobile(String mobile) {
        MemberBasic memberBasicSelect = new MemberBasic();
        memberBasicSelect.setMobile(mobile);
        MemberBasic memberBasic = memberBasicService.selectOne(memberBasicSelect);
        if(memberBasic == null){
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR,"查无此会员!",null);
        }
        MemberBasicVo memberBasicVo = new MemberBasicVo();
        BeanUtils.copyProperties(memberBasic,memberBasicVo);
        return ResultUtils.returnResultSuccess(memberBasicVo);
    }
}
