package com.zhang.thresh.server.test.feign.fallback;

import com.zhang.thresh.common.core.annotation.Fallback;
import com.zhang.thresh.server.test.feign.IRemoteUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 调用远程的获取用户列表失败回调
 * @author MrZhang
 */
@Slf4j
@Fallback
public class RemoteUserServiceFallback implements FallbackFactory<IRemoteUserService> {

    @Override
    public IRemoteUserService create(Throwable throwable) {
        return (queryRequest, user) -> {
            log.error("获取用户信息失败", throwable);
            return null;
        };
    }
}
