package com.zhang.thresh.server.test.feign.fallback;

import com.zhang.thresh.server.test.feign.IRemoteTradeLogService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 调用远程的打包拍送方法失败回调
 * @author MrZhang
 */
@Slf4j
@Component
public class RemoteTradeLogServiceFallback implements FallbackFactory<IRemoteTradeLogService> {
    @Override
    public IRemoteTradeLogService create(Throwable throwable) {
        return tradeLog -> log.error("调用失败", throwable);
    }
}
