package com.zhang.thresh.common.security.starter.annotation;

import com.zhang.thresh.common.security.starter.configure.ThreshCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 资源服务器注解 表示该服务为OAuth2资源服务器
 * @author MrZhang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThreshCloudResourceServerConfigure.class)
public @interface EnableThreshCloudResourceServer {
}
