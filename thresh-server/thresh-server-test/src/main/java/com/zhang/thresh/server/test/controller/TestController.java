package com.zhang.thresh.server.test.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.system.SystemUser;
import com.zhang.thresh.common.core.entity.system.TradeLog;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.test.feign.IRemoteUserService;
import com.zhang.thresh.server.test.service.ITradeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MrZhang
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final IRemoteUserService remoteUserService;
    private final ITradeLogService tradeLogService;

    /**
     * 用于演示 Feign调用受保护的远程方法
     */
    @GetMapping("user/list")
    public ThreshResponse getRemoteUserList(QueryRequest request, SystemUser user) {
        return remoteUserService.userList(request, user);
    }

    /**
     * 测试分布式事务
     */
    @GetMapping("pay")
    public void orderAndPay(TradeLog tradeLog) {
        this.tradeLogService.orderAndPay(tradeLog);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("user")
    public Map<String, Object> currentUser() {
        Map<String, Object> map = new HashMap<>(5);
        map.put("currentUser", ThreshUtil.getCurrentUser());
        map.put("currentUsername", ThreshUtil.getCurrentUsername());
        map.put("currentUserAuthority", ThreshUtil.getCurrentUserAuthority());
        map.put("currentTokenValue", ThreshUtil.getCurrentTokenValue());
        map.put("currentRequestIpAddress", ThreshUtil.getHttpServletRequestIpAddress());
        return map;
    }
}
