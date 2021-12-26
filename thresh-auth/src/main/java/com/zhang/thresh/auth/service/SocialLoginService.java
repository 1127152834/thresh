package com.zhang.thresh.auth.service;

import com.zhang.thresh.auth.entity.BindUser;
import com.zhang.thresh.auth.entity.UserConnection;
import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.exception.ThreshException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

/**
 * @author MrZhang
 */
public interface SocialLoginService {

    /**
     * 解析第三方登录请求
     *
     * @param oauthType 第三方平台类型
     * @return AuthRequest
     * @throws ThreshException 异常
     */
    AuthRequest renderAuth(String oauthType) throws ThreshException;

    /**
     * 处理第三方登录（绑定页面）
     *
     * @param oauthType 第三方平台类型
     * @param callback  回调
     * @return ThreshResponse
     * @throws ThreshException 异常
     */
    ThreshResponse resolveBind(String oauthType, AuthCallback callback) throws ThreshException;

    /**
     * 处理第三方登录（登录页面）
     *
     * @param oauthType 第三方平台类型
     * @param callback  回调
     * @return ThreshResponse
     * @throws ThreshException 异常
     */
    ThreshResponse resolveLogin(String oauthType, AuthCallback callback) throws ThreshException;

    /**
     * 绑定并登录
     *
     * @param bindUser 绑定用户
     * @param authUser 第三方平台对象
     * @return OAuth2AccessToken 令牌对象
     * @throws ThreshException 异常
     */
    OAuth2AccessToken bindLogin(BindUser bindUser, AuthUser authUser) throws ThreshException;

    /**
     * 注册并登录
     *
     * @param registUser 注册用户
     * @param authUser   第三方平台对象
     * @return OAuth2AccessToken 令牌对象
     * @throws ThreshException 异常
     */
    OAuth2AccessToken signLogin(BindUser registUser, AuthUser authUser) throws ThreshException;

    /**
     * 绑定
     *
     * @param bindUser 绑定对象
     * @param authUser 第三方平台对象
     * @throws ThreshException 异常
     */
    void bind(BindUser bindUser, AuthUser authUser) throws ThreshException;

    /**
     * 解绑
     *
     * @param bindUser  绑定对象
     * @param oauthType 第三方平台对象
     * @throws ThreshException 异常
     */
    void unbind(BindUser bindUser, String oauthType) throws ThreshException;

    /**
     * 根据用户名获取绑定关系
     *
     * @param username 用户名
     * @return 绑定关系集合
     */
    List<UserConnection> findUserConnections(String username);
}
