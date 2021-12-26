package com.zhang.thresh.server.system.aspect;

import com.zhang.thresh.common.core.exception.ThreshException;
import com.zhang.thresh.common.core.utils.ThreshUtil;
import com.zhang.thresh.server.system.annotation.ControllerEndpoint;
import com.zhang.thresh.server.system.service.ILogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 处理被@ControllerEndpoint注解的方法
 * @author MrZhang
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ControllerEndpointAspect extends BaseAspectSupport {

    /**
     * 记录日志
     */
    private final ILogService logService;

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.zhang.thresh.server.system.controller.*.*(..)) && @annotation(com.zhang.thresh.server.system.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    /**
     * @Around("pointcut()") 在指定切入点进行拦截
     * @param point
     * @return
     * @throws ThreshException
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws ThreshException {
        Object result;
        /**
         * 获取方法
         */
        Method targetMethod = resolveMethod(point);
        /**
         * 获取方法中的@ControllerEndpoint对象
         */
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        /**
         * 获取当前动作描述
         */
        String operation = annotation.operation();
        /**
         * 记录方法执行开始时间
         */
        long start = System.currentTimeMillis();
        try {
            /**
             * 执行方法
             */
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                /**
                 * 记录方法执行人
                 */
                String username = ThreshUtil.getCurrentUsername();
                /**
                 * 记录用户IP
                 */
                String ip = ThreshUtil.getHttpServletRequestIpAddress();
                /**
                 * 存入日志
                 */
                logService.saveLog(point, targetMethod, ip, operation, username, start);
            }
            return result;
        } catch (Throwable throwable) {
            /**
             * 打印执行失败日志
             */
            log.error(throwable.getMessage(), throwable);
            /**
             * 自定义异常描述
             */
            String exceptionMessage = annotation.exceptionMessage();
            /**
             * 系统异常描述
             */
            String message = throwable.getMessage();
            /**
             * 判断该异常为系统异常还是自定义异常，若为系统异常，则抛出系统异常错误，若为自定义异常，则抛出Thresh异常。
             */
            String error = ThreshUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new ThreshException(error);
        }
    }
}



