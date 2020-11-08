package com.frog.backend.server.auth.service.service.impl;

import com.frog.backend.server.auth.service.constant.MessageConstant;
import com.frog.backend.server.auth.service.constant.RegisterOrLoginType;
import com.frog.backend.server.auth.service.pojo.oauth.SecurityUser;
import com.frog.backend.server.member.service.api.MemberService;
import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.service.base.constant.RedisConstants;
import com.frog.backend.server.service.base.constant.SmsCodeType;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.constant.AuthConstant;
import com.frog.backend.server.service.core.exception.ServiceException;
import com.frog.backend.server.service.core.pojo.oauth.UserDto;
import com.frog.backend.server.service.core.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-17
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private RedisConnection redisConnection;

    @DubboReference
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        UserDto userDto = new UserDto();
        MemberBasicVo memberBasicVo = null;
        String type = request.getParameter("type");
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            //todo
            memberBasicVo = new MemberBasicVo();
        } else if (AuthConstant.MEMBER_CLIENT_ID.equals(clientId)) {
            if (RegisterOrLoginType.REGISTER.toString().equals(type)) {
                //注册
                String key = RedisConstants.RedisKeyPrefix.AUTH + RedisConstants.RedisKeyInfix.REGISTER_INFO + username;
                memberBasicVo = (MemberBasicVo) valueOperations.get(key);
                Long count = redisConnection.del(key.getBytes());
                log.info("redisConnection.del(key.getBytes()),key={},count={}", key, count);
            } else {
                //登录
                Result<MemberBasicVo> result = memberService.getMemberInfoByMobile(username);
                if (!ResultUtils.isSuccessResult(result)) {
                    throw new ServiceException(result.getCode(), result.getMsg());
                }
                memberBasicVo = result.getData();
                log.info("memberBasicVo======={}", memberBasicVo);
            }
        }
        userDto.setId(memberBasicVo.getMemberId());
        userDto.setUsername(memberBasicVo.getMobile());
        userDto.setPassword(passwordEncoder.encode(memberBasicVo.getPassword()));
        if(RegisterOrLoginType.LOGIN_BY_SMS_CODE.toString().equals(type)){
            String key = RedisConstants.RedisKeyPrefix.AUTH + RedisConstants.RedisKeyInfix.SMS_CODE +
                    SmsCodeType.LOGIN + ":" + memberBasicVo.getMobile();
            String smsCode = (String) valueOperations.get(key);
            userDto.setPassword(passwordEncoder.encode(smsCode));
        }
        userDto.setStatus(1);
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }

        return securityUser;
    }
}
