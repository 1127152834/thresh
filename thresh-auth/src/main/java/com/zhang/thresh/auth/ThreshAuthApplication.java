package com.zhang.thresh.auth;

import com.zhang.thresh.common.security.starter.annotation.EnableThreshCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 张天成PC
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableThreshCloudResourceServer
@MapperScan("com.zhang.thresh.auth.mapper")
public class ThreshAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshAuthApplication.class, args);
    }

}
