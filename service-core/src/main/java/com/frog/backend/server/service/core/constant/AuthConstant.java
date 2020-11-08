package com.frog.backend.server.service.core.constant;

/**
 * Description 权限相关常量定义
 *
 * @author yxy
 * @date 2020-10-08
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台会员APP client_id
     */
    String MEMBER_CLIENT_ID = "member-app";

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/admin-service/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     * TOKEN有效时间
     */
    int TOKEN_VALIDITY_SECONDS = 3600 * 24;

    /**
     * 刷新TOKEN有效时间
     */
    int REFRESH_TOKEN_VALIDITY_SECONDS = 3600 * 24 * 7;

}
