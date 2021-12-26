package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.gateway.enhance.entity.BlockLog;
import com.zhang.thresh.gateway.enhance.service.BlockLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 拦截日志
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/blockLog")
public class BlockLogController {

    private final BlockLogService blockLogService;

    /**
     * 查找拦截日志分页数据
     * @param request
     * @param blockLog 过滤参数
     * @return
     */
    @GetMapping("data")
    public Flux<BlockLog> findUserPages(QueryRequest request, BlockLog blockLog) {
        return blockLogService.findPages(request, blockLog);
    }

    /**
     * 查找拦截日志分页数据count
     * @param blockLog 过滤参数
     * @return
     */
    @GetMapping("count")
    public Mono<Long> findUserCount(BlockLog blockLog) {
        return blockLogService.findCount(blockLog);
    }

    /**
     * 删除拦截日志
     * @param ids 日志ID
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<BlockLog> deleteBlockLog(String ids) {
        return blockLogService.delete(ids);
    }
}
