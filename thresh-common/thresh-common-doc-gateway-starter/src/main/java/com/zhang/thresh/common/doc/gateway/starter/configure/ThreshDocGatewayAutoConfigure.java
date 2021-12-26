package com.zhang.thresh.common.doc.gateway.starter.configure;

import com.zhang.thresh.common.doc.gateway.starter.filter.ThreshDocGatewayHeaderFilter;
import com.zhang.thresh.common.doc.gateway.starter.handler.ThreshDocGatewayHandler;
import com.zhang.thresh.common.doc.gateway.starter.properties.ThreshDocGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * 接口文档配置
 * @author MrZhang
 */
@Configuration
@EnableConfigurationProperties(ThreshDocGatewayProperties.class)
@ConditionalOnProperty(value = "Thresh.doc.gateway.enable", havingValue = "true", matchIfMissing = true)
public class ThreshDocGatewayAutoConfigure {

    private final ThreshDocGatewayProperties ThreshDocGatewayProperties;
    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;

    public ThreshDocGatewayAutoConfigure(ThreshDocGatewayProperties ThreshDocGatewayProperties) {
        this.ThreshDocGatewayProperties = ThreshDocGatewayProperties;
    }

    @Autowired(required = false)
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Autowired(required = false)
    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    @Bean
    public ThreshDocGatewayResourceConfigure ThreshDocGatewayResourceConfigure(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        return new ThreshDocGatewayResourceConfigure(routeLocator, gatewayProperties);
    }

    @Bean
    public ThreshDocGatewayHeaderFilter ThreshDocGatewayHeaderFilter() {
        return new ThreshDocGatewayHeaderFilter();
    }

    @Bean
    public ThreshDocGatewayHandler ThreshDocGatewayHandler(SwaggerResourcesProvider swaggerResources) {
        ThreshDocGatewayHandler ThreshDocGatewayHandler = new ThreshDocGatewayHandler();
        ThreshDocGatewayHandler.setSecurityConfiguration(securityConfiguration);
        ThreshDocGatewayHandler.setUiConfiguration(uiConfiguration);
        ThreshDocGatewayHandler.setSwaggerResources(swaggerResources);
        ThreshDocGatewayHandler.setProperties(ThreshDocGatewayProperties);
        return ThreshDocGatewayHandler;
    }
}
