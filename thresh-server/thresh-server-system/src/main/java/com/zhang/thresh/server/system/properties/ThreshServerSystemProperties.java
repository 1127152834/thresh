package com.zhang.thresh.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MrZhang
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:thresh-server-system.properties"})
@ConfigurationProperties(prefix = "thresh.server.system")
public class ThreshServerSystemProperties {
    /**
     * 批量插入当批次可插入的最大值
     */
    private Integer batchInsertMaxNum = 1000;
}
