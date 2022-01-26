package com.zhang.thresh.server.test;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.zhang.thresh.common.security.starter.annotation.EnableThreshCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@SpringBootApplication
@EnableThreshCloudResourceServer
@EnableTransactionManagement
@EnableDistributedTransaction
@MapperScan("com.zhang.thresh.server.test.mapper")
public class ThreshServerTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreshServerTestApplication.class, args);
    }
}
