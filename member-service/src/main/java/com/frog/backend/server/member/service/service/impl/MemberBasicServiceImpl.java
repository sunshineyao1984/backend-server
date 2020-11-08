package com.frog.backend.server.member.service.service.impl;

import com.frog.backend.components.mysql.component.mapper.BaseMapper;
import com.frog.backend.components.mysql.component.service.impl.BaseServiceImpl;
import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.service.base.constant.SmsCodeType;
import com.frog.backend.server.member.service.pojo.domain.MemberBasic;
import com.frog.backend.server.member.service.pojo.param.SmsCodeParam;
import com.frog.backend.server.member.service.service.MemberBasicService;
import com.frog.backend.server.member.service.mapper.MemberBasicMapper;
import com.frog.backend.server.service.base.constant.RedisConstants;
import com.frog.backend.server.service.base.util.RandomUtils;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description MemberBasicServiceImpl
 *
 * @author yxy
 * @date 2020/10/11
 */
@Slf4j
@Service
public class MemberBasicServiceImpl extends BaseServiceImpl<MemberBasic, Long> implements MemberBasicService {

    @Autowired
    private MemberBasicMapper memberBasicMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    protected BaseMapper<MemberBasic> getMapper() {
        return memberBasicMapper;
    }

    @Override
    public Result<MemberBasicVo> register(String mobile, String password, String smsCode) {
        if (!verifySmsCode(mobile, smsCode, SmsCodeType.REGISTER)) {
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR, "验证码有误！", null);
        }
        //查询手机号是否已注册
        MemberBasic memberBasicSelect = new MemberBasic();
        memberBasicSelect.setMobile(mobile);
        MemberBasic memberBasic = selectOne(memberBasicSelect);
        if (memberBasic != null) {
            return ResultUtils.returnResultDynamic(CodeEnum.ERROR, "该手机号已注册！", null);
        }
        //未注册进行添加操作
        MemberBasic memberBasicNew = new MemberBasic();
        memberBasicNew.setMobile(mobile);
        memberBasicNew.setPassword(password);
        insertSelective(memberBasicNew);
        MemberBasicVo memberBasicVo = new MemberBasicVo();
        BeanUtils.copyProperties(memberBasicNew, memberBasicVo);
        return ResultUtils.returnResultSuccess(memberBasicVo);
    }

    @Override
    public Result<Map<String, String>> generateSmsCode(SmsCodeParam smsCodeParam) {
        String key = RedisConstants.RedisKeyPrefix.AUTH + RedisConstants.RedisKeyInfix.SMS_CODE +
                smsCodeParam.getSmsCodeType() + ":" + smsCodeParam.getMobile();
        //生成4位随机数字字符串
        String rd = RandomUtils.randomNumbers(4);
        long expire = RedisConstants.RedisExpire.SMS_CODE;
        valueOperations.set(key, rd, expire, TimeUnit.SECONDS);
        Map<String, String> map = new HashMap<>(1);
        if (smsCodeParam.getSmsCodeType().equals(SmsCodeType.REGISTER)) {
            map.put("smsMsg", "您的注册验证码是：" + rd);
        } else if (smsCodeParam.getSmsCodeType().equals(SmsCodeType.LOGIN)) {
            map.put("smsMsg", "您的登录验证码是：" + rd);
        }
        // TODO 真实情况是通过短信通知用户，这里暂且通过返回值通知用户
        return ResultUtils.returnResultSuccess(map);
    }

    /**
     * 验证用户输入的短信验证码
     *
     * @param mobile
     * @param smsCode     用户输入的短信验证码
     * @param smsCodeType
     * @return
     */
    private boolean verifySmsCode(String mobile, String smsCode, Integer smsCodeType) {
        if (StringUtils.isBlank(smsCode)) {
            return false;
        }
        String realSmsCode = getSmsCodeFromRedis(mobile, smsCodeType);
        return smsCode.equals(realSmsCode);
    }

    /**
     * 从Redis获取短信验证码
     *
     * @param mobile
     * @param smsCodeType
     * @return
     */
    private String getSmsCodeFromRedis(String mobile, Integer smsCodeType) {
        String key = RedisConstants.RedisKeyPrefix.AUTH + RedisConstants.RedisKeyInfix.SMS_CODE +
                smsCodeType + ":" + mobile;
        String smsCode = (String) valueOperations.get(key);
        return smsCode;
    }
}