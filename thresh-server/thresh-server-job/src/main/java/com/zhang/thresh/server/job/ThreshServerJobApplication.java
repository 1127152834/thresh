package com.zhang.thresh.server.job;

import com.zhang.thresh.common.security.starter.annotation.EnableThreshCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableThreshCloudResourceServer
@MapperScan("com.zhang.thresh.server.job.mapper")
public class ThreshServerJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshServerJobApplication.class, args);
    }

}
