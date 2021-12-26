package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.RateLimitRule;
import com.zhang.thresh.gateway.enhance.service.RateLimitRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 限流规则
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/rateLimitRule")
public class RateLimitRuleController {

    private final RateLimitRuleService rateLimitRuleService;

    /**
     * 获取限流规则分页数据
     * @param request
     * @param rateLimitRule
     * @return
     */
    @GetMapping("data")
    public Flux<RateLimitRule> findUserPages(QueryRequest request, RateLimitRule rateLimitRule) {
        return rateLimitRuleService.findPages(request, rateLimitRule);
    }

    /**
     * 限流规则分页count
     * @param rateLimitRule
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findUserCount(RateLimitRule rateLimitRule) {
        return rateLimitRuleService.findCount(rateLimitRule);
    }

    /**
     * 查找限流规则
     * @param requestUri 请求url 请求方法
     * @param requestMethod
     * @return
     */
    @GetMapping("exist")
    public Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod) {
        return rateLimitRuleService.findByRequestUriAndRequestMethod(requestUri, requestMethod);
    }

    /**
     * 创建限流规则
     * @param rateLimitRule 创建的限流规则
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<RateLimitRule> createRateLimitRule(RateLimitRule rateLimitRule) {
        return rateLimitRuleService.create(rateLimitRule);
    }

    /**
     * 更新限流规则
     * @param rateLimitRule 更新的限流规则
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<RateLimitRule> updateRateLimitRule(RateLimitRule rateLimitRule) {
        return rateLimitRuleService.update(rateLimitRule);
    }

    /**
     * 删除限流规则
     * @param ids 规则ID
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<RateLimitRule> deleteRateLimitRule(String ids) {
        return rateLimitRuleService.delete(ids);
    }
}
