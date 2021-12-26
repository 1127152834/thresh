package com.zhang.thresh.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author MrZhang
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:thresh-auth.properties"})
@ConfigurationProperties(prefix = "thresh.auth")
public class ThreshAuthProperties {
    /**
     * 验证码配置
     */
    private com.zhang.thresh.auth.properties.ThreshValidateCodeProperties code = new com.zhang.thresh.auth.properties.ThreshValidateCodeProperties();
    /**
     * JWT加签密钥
     */
    private String jwtAccessKey;
    /**
     * 是否使用 JWT令牌
     */
    private Boolean enableJwt;

    /**
     * 社交登录所使用的 Client
     */
    private String socialLoginClientId;
}
