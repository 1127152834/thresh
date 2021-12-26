package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.BlackList;
import com.zhang.thresh.gateway.enhance.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 黑名单
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/blackList")
public class BlackListController {

    private final BlackListService blackListService;

    /**
     * 黑名单分页数据
     * @param request
     * @param blackList 查询参数
     * @return
     */
    @GetMapping("data")
    public Flux<BlackList> findUserPages(QueryRequest request, BlackList blackList) {
        return blackListService.findPages(request, blackList);
    }

    /**
     * 黑名单分页count
     * @param blackList 查询参数
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findUserCount(BlackList blackList) {
        return blackListService.findCount(blackList);
    }

    /**
     * 根据条件查找黑名单
     * @param ip ip地址
     * @param requestUri 请求地址
     * @param requestMethod 请求方法
     * @return
     */
    @GetMapping("exist")
    public Flux<BlackList> findByCondition(String ip, String requestUri, String requestMethod) {
        return blackListService.findByCondition(ip, requestUri, requestMethod);
    }

    /**
     * 创建黑名单
     * @param blackList 创建的黑名单
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<BlackList> createBlackList(BlackList blackList) {
        return blackListService.create(blackList);
    }

    /**
     * 更新黑名单
     * @param blackList 更新的黑名单
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin')")
    public Mono<BlackList> updateBlackList(BlackList blackList) {
        return blackListService.update(blackList);
    }

    /**
     * 删除黑名单
     * @param ids 黑名单ID
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<BlackList> deleteBlackList(String ids) {
        return blackListService.delete(ids);
    }

}
