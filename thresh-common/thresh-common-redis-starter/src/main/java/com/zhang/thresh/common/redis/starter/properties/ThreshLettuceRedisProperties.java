package com.zhang.thresh.common.redis.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MrZhang
 */
@ConfigurationProperties(prefix = "thresh.lettuce.redis")
public class ThreshLettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ThreshLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
