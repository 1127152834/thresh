package com.zhang.thresh.common.core.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 呗Fallback标识的类是一个有回滚行为的类
 * @author MrZhang
 * @Targer({ElementType.TYPE}) 用于描述类、接口(包括注解类型) 或enum声明
 * @Retention(RetentionPolicy.RUNTIME)  注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 * @Documented 生成文档信息的时候保留注解，对类作辅助说明
 * @Component 实现bean的注入
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Fallback {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
