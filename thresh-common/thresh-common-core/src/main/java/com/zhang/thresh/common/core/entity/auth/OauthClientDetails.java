package com.zhang.thresh.common.core.entity.auth;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 客户端信息
 *
 * @author MrZhang
 * @date 2019-09-09 14:13:23
 */
@Data
@TableName("oauth_client_details")
public class OauthClientDetails {

    /**
     * 客户端标识
     */
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    /**
     * 能访问的资源id集合，多个资源时用逗号(,)分隔
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 指定客户端(client)的访问密匙
     */
    @TableField("client_secret")
    private String clientSecret;

    /**
     * 指定客户端申请的权限范围,可选值包括read,write,trust;
     */
    @TableField("scope")
    private String scope;

    /**
     * 指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔
     */
    @TableField("authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致
     */
    @TableField("web_server_redirect_uri")
    private String webServerRedirectUri;

    /**
     * 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔
     */
    @TableField("authorities")
    private String authorities;

    /**
     * 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
     */
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
     */
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据
     */
    @TableField("additional_information")
    private String additionalInformation;

    /**
     * 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'.
     * 该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权.
     */
    @TableField("autoapprove")
    private Byte autoapprove;

}
