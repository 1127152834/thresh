package com.zhang.thresh.common.logging.starter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 收集控制器产生的日志
 * @author MrZhang
 */
@Aspect
public class ControllerLogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 增强处理 用于记录日志
     * 被@Controller @RestController 或是在controller包下面的方法 都会进入到该方法
     * @param pjp 当前被拦截的方法
     * @return
     * @throws Throwable
     */
    @Around("(@within(org.springframework.stereotype.Controller)" +
            "|| @within(org.springframework.web.bind.annotation.RestController))" +
            "&& execution(public * com.zhang..*.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //获取当前方法的类名
        String className = pjp.getTarget().getClass().getName();
        //获取当前方法名
        String methodName = pjp.getSignature().getName();
        //记录方法执行开始时间
        long beginTime = System.currentTimeMillis();
        Object returnValue = null;
        Exception ex = null;
        try {
            //执行目标方法
            returnValue = pjp.proceed();
            return returnValue;
        } catch (Exception e) {
            //捕获异常 可以做相应的处理，这里没有做
            ex = e;
            throw e;
        } finally {
            //记录日志 方法名，参数，耗时 返回值/异常
            long cost = System.currentTimeMillis() - beginTime;
            if (ex != null) {
                log.error("[class: {}][method: {}][cost: {}ms][args: {}][发生异常]",
                        className, methodName, pjp.getArgs(), ex);
            } else {
                log.info("[class: {}][method: {}][cost: {}ms][args: {}][return: {}]",
                        className, methodName, cost, pjp.getArgs(), returnValue);
            }
        }

    }
}
