package com.frog.backend.server.auth.service.service.impl;

import com.frog.backend.server.auth.service.constant.RegisterOrLoginType;
import com.frog.backend.server.auth.service.pojo.param.RegisterOrLoginParam;
import com.frog.backend.server.auth.service.service.AuthService;
import com.frog.backend.server.member.service.api.MemberService;
import com.frog.backend.server.member.service.api.pojo.vo.MemberBasicVo;
import com.frog.backend.server.service.base.constant.RedisConstants;
import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.constant.AuthConstant;
import com.frog.backend.server.service.core.pojo.oauth.Oauth2TokenDto;
import com.frog.backend.server.service.core.pojo.vo.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-24
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @DubboReference
    private MemberService memberService;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public Result<Oauth2TokenDto> registerOrLogin(Principal principal, RegisterOrLoginParam registerOrLoginParam) throws HttpRequestMethodNotSupportedException {
        //注册需要将注册信息保存到会员服务
        if (registerOrLoginParam.getType().equals(RegisterOrLoginType.REGISTER)) {
            Result<MemberBasicVo> result = memberService.register(registerOrLoginParam.getUsername(),
                    registerOrLoginParam.getPassword(),registerOrLoginParam.getSmsCode());
            if (!ResultUtils.isSuccessResult(result)) {
                return ResultUtils.returnResultFromBaseResult(result, null);
            }
            //注册成功后将注册信息存入Redis
            String key = RedisConstants.RedisKeyPrefix.AUTH + RedisConstants.RedisKeyInfix.REGISTER_INFO + registerOrLoginParam.getUsername();
            valueOperations.set(key, result.getData());
        }

        Map<String, String> oauthParams = new HashMap<>(4);
        oauthParams.put("grant_type", registerOrLoginParam.getGrant_type());
        oauthParams.put("client_id", registerOrLoginParam.getClient_id());
        oauthParams.put("client_secret", registerOrLoginParam.getClient_secret());
        oauthParams.put("username", registerOrLoginParam.getUsername());
        oauthParams.put("password", registerOrLoginParam.getPassword());
        //短信验证码登录时，password字段用smsCode填充
        if(registerOrLoginParam.getType().equals(RegisterOrLoginType.LOGIN_BY_SMS_CODE)){
            oauthParams.put("password", registerOrLoginParam.getSmsCode());
        }
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, oauthParams).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        return ResultUtils.returnResultSuccess(oauth2TokenDto);
    }


}
