package com.zhang.thresh.common.security.starter.configure;

import com.zhang.thresh.common.security.starter.interceptor.ThreshServerProtectInterceptor;
import com.zhang.thresh.common.security.starter.properties.ThreshCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MrZhang
 */
public class ThreshCloudSecurityInteceptorConfigure implements WebMvcConfigurer {

    private ThreshCloudSecurityProperties properties;

    @Autowired
    public void setProperties(ThreshCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor threshServerProtectInterceptor() {
        ThreshServerProtectInterceptor threshServerProtectInterceptor = new ThreshServerProtectInterceptor();
        threshServerProtectInterceptor.setProperties(properties);
        return threshServerProtectInterceptor;
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(threshServerProtectInterceptor());
    }
}
