package com.zhang.thresh.server.test.feign;

import com.zhang.thresh.common.core.entity.constant.ThreshServerConstant;
import com.zhang.thresh.common.core.entity.system.TradeLog;
import com.zhang.thresh.server.test.feign.fallback.RemoteTradeLogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 调用远程system服务的send方法
 * @author MrZhang
 */
@FeignClient(value = ThreshServerConstant.THRESH_SERVER_SYSTEM, contextId = "tradeLogServiceClient", fallbackFactory = RemoteTradeLogServiceFallback.class)
public interface IRemoteTradeLogService {

    /**
     * 打包派送
     *
     * @param tradeLog 交易日志
     */
    @PostMapping("package/send")
    void packageAndSend(@RequestBody TradeLog tradeLog);
}
