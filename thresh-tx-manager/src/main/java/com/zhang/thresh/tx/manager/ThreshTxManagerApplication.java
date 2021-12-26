package com.zhang.thresh.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张天成PC
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class ThreshTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshTxManagerApplication.class, args);
    }

}
