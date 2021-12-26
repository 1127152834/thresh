package com.zhang.thresh.server.job.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于从 IOC容器中获取 Bean
 *
 * @author MrZhang
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    /**
     * 获取上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * 根据对象名获取bean
     * @param name 对象名
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据类名获取bean
     * @param clazz 类名
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据对象和类名获取bean
     * @param name 对象名
     * @param requiredType 类名
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 判断是否存在指定名称的bean
     * @param name 对象名
     * @return
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * bean是否为单例
     * @param name
     * @return
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取对象的类
     * @param name
     * @return
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 设置上下文
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

}
