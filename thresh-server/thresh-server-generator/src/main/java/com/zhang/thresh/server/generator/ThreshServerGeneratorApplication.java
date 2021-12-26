package com.zhang.thresh.server.generator;

import com.zhang.thresh.common.security.starter.annotation.EnableThreshCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张天成PC
 */
@SpringBootApplication
@EnableThreshCloudResourceServer
@MapperScan("com.zhang.thresh.server.generator.mapper")
public class ThreshServerGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshServerGeneratorApplication.class, args);
    }

}
