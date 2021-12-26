package com.zhang.thresh.common.doc.gateway.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 参数配置
 * @author MrZhang
 */
@ConfigurationProperties(prefix = "thresh.doc.gateway")
public class ThreshDocGatewayProperties {

    /**
     * 是否开启网关文档聚合功能
     */
    private Boolean enable;

    /**
     * 需要暴露doc的服务列表，多个值时用逗号分隔
     * 如 Thresh-Auth,Thresh-Server-System
     */
    private String resources;

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ThreshDocGatewayProperties{" +
                "enable=" + enable +
                ", resources='" + resources + '\'' +
                '}';
    }
}
