package com.zhang.thresh.server.system;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.zhang.thresh.common.security.starter.annotation.EnableThreshCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 张天成PC
 */
@EnableAsync
@SpringBootApplication
@EnableThreshCloudResourceServer
@EnableTransactionManagement
@EnableDistributedTransaction
@MapperScan("com.zhang.thresh.server.system.mapper")
public class ThreshServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshServerSystemApplication.class, args);
    }

}
