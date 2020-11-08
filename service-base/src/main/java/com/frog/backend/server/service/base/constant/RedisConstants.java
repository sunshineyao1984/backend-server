package com.frog.backend.server.service.base.constant;

/**
 * Description
 *
 * @author yxy
 * @date 2020-10-13
 */
public interface RedisConstants {

    /**
     * RedisKey前缀
     */
    interface RedisKeyPrefix {
        /**
         * 会员服务RedisKey前缀
         */
        String MEMBER = "member:";
        /**
         * 认证服务RedisKey前缀
         */
        String AUTH = "auth:";
        /**
         * 名片服务RedisKey前缀
         */
        String CARD = "card:";
    }

    /**
     * RedisKey中缀
     */
    interface RedisKeyInfix {
        /**
         * 短信验证码
         */
        String SMS_CODE = "sms_code_";
        /**
         * 注册信息
         */
        String REGISTER_INFO = "register_info:";
    }


    interface RedisExpire {

        /**
         * 短信验证码有效期 10分钟
         */
        long SMS_CODE = 60 * 10;

    }
}
