package com.zhang.thresh.common.security.starter.configure;

import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.common.security.starter.handler.ThreshAccessDeniedHandler;
import com.zhang.thresh.common.security.starter.handler.ThreshAuthExceptionEntryPoint;
import com.zhang.thresh.common.security.starter.properties.ThreshCloudSecurityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Base64Utils;

/**
 * @author MrZhang
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(ThreshCloudSecurityProperties.class)
@ConditionalOnProperty(value = "thresh.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class ThreshCloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ThreshAccessDeniedHandler accessDeniedHandler() {
        return new ThreshAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public ThreshAuthExceptionEntryPoint authenticationEntryPoint() {
        return new ThreshAuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ThreshCloudSecurityInteceptorConfigure threshCloudSecurityInteceptorConfigure() {
        return new ThreshCloudSecurityInteceptorConfigure();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public ThreshUserInfoTokenServices threshUserInfoTokenServices(ResourceServerProperties properties) {
        return new ThreshUserInfoTokenServices(properties.getUserInfoUri(), properties.getClientId());
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(ThreshConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(ThreshConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = ThreshUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, ThreshConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
