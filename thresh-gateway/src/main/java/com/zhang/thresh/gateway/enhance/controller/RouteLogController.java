package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.RouteLog;
import com.zhang.thresh.gateway.enhance.service.RouteLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 路由日志
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/log")
public class RouteLogController {

    private final RouteLogService routeLogService;

    /**
     * 查找路由日志分页数据
     * @param request
     * @param routeLog 查询条件
     * @return
     */
    @GetMapping("data")
    public Flux<RouteLog> findRouteLogsPages(QueryRequest request, RouteLog routeLog) {
        return routeLogService.findPages(request, routeLog);
    }

    /**
     * 查找路由分页数据count
     * @param routeLog 查询条件
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findRouteLogsCount(RouteLog routeLog) {
        return routeLogService.findCount(routeLog);
    }

    /**
     * 删除路由日志
     * @param ids 日志ID
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<RouteLog> deleteRouteLogs(String ids) {
        return routeLogService.delete(ids);
    }
}
