package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.RouteUser;
import com.zhang.thresh.gateway.enhance.service.RouteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 路由用户
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/user")
public class RouteUserController {

    private final RouteUserService routeUserService;

    /**
     * 查找路由用户分页数据
     * @param request
     * @param routeUser
     * @return
     */
    @GetMapping("data")
    public Flux<RouteUser> findUserPages(QueryRequest request, RouteUser routeUser) {
        return routeUserService.findPages(request, routeUser);
    }

    /**
     * 查找路由用户分页数据count
     * @param routeUser
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findUserCount(RouteUser routeUser) {
        return routeUserService.findCount(routeUser);
    }

    /**
     * 根据用户名获取路由用户
     * @param username 用户名
     * @return
     */
    @GetMapping("{username}")
    public Mono<RouteUser> findByUsername(@PathVariable String username) {
        return routeUserService.findByUsername(username);
    }

    /**
     * 创建路由用户
     * @param routeUser 用户
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<RouteUser> createRouteUser(RouteUser routeUser) {
        return routeUserService.create(routeUser);
    }

    /**
     * 更新路由用户
     * @param routeUser 用户
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<RouteUser> updateRouteUser(RouteUser routeUser) {
        return routeUserService.update(routeUser);
    }

    /**
     * 删除路由用户
     * @param ids 用户id
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<RouteUser> deleteRouteUser(String ids) {
        return routeUserService.delete(ids);
    }
}
