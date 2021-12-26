package com.zhang.thresh.server.test.feign;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.ThreshServerConstant;
import com.zhang.thresh.common.core.entity.system.SystemUser;
import com.zhang.thresh.server.test.feign.fallback.RemoteUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用远程system服务的userList方法
 * @author MrZhang
 */
@FeignClient(value = ThreshServerConstant.THRESH_SERVER_SYSTEM, contextId = "userServiceClient", fallbackFactory = RemoteUserServiceFallback.class)
public interface IRemoteUserService {

    /**
     * remote /user endpoint
     *
     * @param queryRequest queryRequest
     * @param user         user
     * @return ThreshResponse
     */
    @GetMapping("user")
    ThreshResponse userList(@RequestParam("queryRequest") QueryRequest queryRequest, @RequestParam("user") SystemUser user);
}
