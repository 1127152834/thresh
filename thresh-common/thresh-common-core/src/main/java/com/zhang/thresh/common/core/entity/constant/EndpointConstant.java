package com.zhang.thresh.common.core.entity.constant;

/**
 * 端点常量
 *
 * @author MrZhang
 */
public interface EndpointConstant {

    /**
     * 所有端点
     */
    String ALL = "/**";
    /**
     * 权限模块的所有端点
     */
    String OAUTH_ALL = "/oauth/**";
    /**
     * 权限模块中的认证功能
     */
    String OAUTH_AUTHORIZE = "/oauth/authorize";
    /**
     * 权限模块中的token校验功能
     */
    String OAUTH_CHECK_TOKEN = "/oauth/check_token";
    /**
     * 权限模块中的授权功能
     */
    String OAUTH_CONFIRM_ACCESS = "/oauth/confirm_access";
    /**
     * 权限模块中的获取token功能
     */
    String OAUTH_TOKEN = "/oauth/token";
    /**
     * 权限模块中的获取token key功能
     */
    String OAUTH_TOKEN_KEY = "/oauth/token_key";
    /**
     * 权限模块中的认证失败功能
     */
    String OAUTH_ERROR = "/oauth/error";
    /**
     * 健康检查模块的所有端点
     */
    String ACTUATOR_ALL = "/actuator/**";
    /**
     * 登录功能
     */
    String LOGIN = "/login";
}
