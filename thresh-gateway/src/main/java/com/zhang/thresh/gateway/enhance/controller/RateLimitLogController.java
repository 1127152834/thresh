package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.RateLimitLog;
import com.zhang.thresh.gateway.enhance.service.RateLimitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 限流日志
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/rateLimitLog")
public class RateLimitLogController {

    private final RateLimitLogService rateLimitLogService;

    /**
     * 查找限流日志分页数据
     * @param request
     * @param rateLimitLog 查询条件
     * @return
     */
    @GetMapping("data")
    public Flux<RateLimitLog> findUserPages(QueryRequest request, RateLimitLog rateLimitLog) {
        return rateLimitLogService.findPages(request, rateLimitLog);
    }

    /**
     * 查找限流日志分页数据count
     * @param rateLimitLog 查询条件
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findUserCount(RateLimitLog rateLimitLog) {
        return rateLimitLogService.findCount(rateLimitLog);
    }

    /**
     * 删除日志
     * @param ids 日志ID
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<RateLimitLog> deleteRateLimitLog(String ids) {
        return rateLimitLogService.delete(ids);
    }
}
