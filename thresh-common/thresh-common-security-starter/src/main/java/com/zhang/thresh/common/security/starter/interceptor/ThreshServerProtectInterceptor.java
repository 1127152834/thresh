package com.zhang.thresh.common.security.starter.interceptor;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.common.security.starter.properties.ThreshCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MrZhang
 */
public class ThreshServerProtectInterceptor implements HandlerInterceptor {

    private ThreshCloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(ThreshConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(ThreshConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            ThreshResponse threshResponse = new ThreshResponse();
            ThreshUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, threshResponse.message("请通过网关获取资源"));
            return false;
        }
    }

    public void setProperties(ThreshCloudSecurityProperties properties) {
        this.properties = properties;
    }
}
