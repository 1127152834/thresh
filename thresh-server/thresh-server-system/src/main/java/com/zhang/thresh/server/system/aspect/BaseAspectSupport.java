package com.zhang.thresh.server.system.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 对端点所处的方法进行解析
 * @author MrZhang
 */
public abstract class BaseAspectSupport {

    /**
     * 解析方法
     * @param point
     * @return
     */
    Method resolveMethod(ProceedingJoinPoint point) {
        /**
         * 获取方法和类
         */
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        /**
         * 查找并返回方法
         */
        Method method = getDeclaredMethod(targetClass, signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("无法解析目标方法: " + signature.getMethod().getName());
        }
        return method;
    }

    private Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            /**
             * 通过反射机制获取指定方法
             */
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                /**
                 * 向父类继续获取
                 */
                return getDeclaredMethod(superClass, name, parameterTypes);
            }
        }
        return null;
    }
}
