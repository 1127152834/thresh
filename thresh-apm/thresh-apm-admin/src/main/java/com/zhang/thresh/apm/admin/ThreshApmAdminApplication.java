package com.zhang.thresh.apm.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张天成PC
 */
@EnableAdminServer
@SpringBootApplication
public class ThreshApmAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreshApmAdminApplication.class, args);
    }

}
