package com.zhang.thresh.common.security.starter.handler;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MrZhang
 */
@Slf4j
public class ThreshAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        ThreshUtil.makeJsonResponse(response, status, new ThreshResponse().message(message));
    }
}
