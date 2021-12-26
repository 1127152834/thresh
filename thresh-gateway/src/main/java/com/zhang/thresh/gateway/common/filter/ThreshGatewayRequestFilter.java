package com.zhang.thresh.gateway.common.filter;

import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.gateway.enhance.service.RouteEnhanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关过滤器
 * @author MrZhang
 */
@Slf4j
@Component
@Order(0)
@RequiredArgsConstructor
public class ThreshGatewayRequestFilter implements GlobalFilter {

    private final RouteEnhanceService routeEnhanceService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Value("${thresh.gateway.enhance:false}")
    private Boolean routeEhance;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //是否路由增强
        if (routeEhance) {
            //判断该ip是否被列入黑名单
            Mono<Void> blackListResult = routeEnhanceService.filterBlackList(exchange);
            if (blackListResult != null) {
                routeEnhanceService.saveBlockLogs(exchange);
                return blackListResult;
            }
            //判断用户是否被限流
            Mono<Void> rateLimitResult = routeEnhanceService.filterRateLimit(exchange);
            if (rateLimitResult != null) {
                routeEnhanceService.saveRateLimitLogs(exchange);
                return rateLimitResult;
            }

            //通过过滤 保存请求日志
            routeEnhanceService.saveRequestLogs(exchange);
        }

        //添加gateway的token，这一步是为了过滤不是通过网关进入的请求
        byte[] token = Base64Utils.encode((ThreshConstant.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(token)};
        ServerHttpRequest build = exchange.getRequest().mutate().header(ThreshConstant.GATEWAY_TOKEN_HEADER, headerValues).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }
}
