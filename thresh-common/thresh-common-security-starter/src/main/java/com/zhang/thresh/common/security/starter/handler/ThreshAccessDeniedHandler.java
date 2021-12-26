package com.zhang.thresh.common.security.starter.handler;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MrZhang
 */
public class ThreshAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ThreshResponse threshResponse = new ThreshResponse();
        ThreshUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, threshResponse.message("没有权限访问该资源"));
    }
}
