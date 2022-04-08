package com.zhang.thresh.server.scanner;

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
@MapperScan("com.zhang.thresh.server.scanner.mapper")
public class ThreshServerScannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshServerScannerApplication.class, args);
    }

}
