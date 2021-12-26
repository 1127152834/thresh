package com.zhang.thresh.gateway.enhance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 黑名单
 * @author MrZhang
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {

    public static final int CLOSE = 0;
    public static final int OPEN = 1;

    public static final String METHOD_ALL = "all";

    @Id
    private String id;
    /**
     * 黑名单ip
     */
    private String ip;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 请求方法，如果为ALL则表示对所有方法生效
     */
    private String requestMethod;
    /**
     * 限制时间起
     */
    private String limitFrom;
    /**
     * 限制时间止
     */
    private String limitTo;
    /**
     * ip对应地址
     */
    private String location;
    /**
     * 状态，0关闭，1开启
     */
    private String status;
    /**
     * 规则创建时间
     */
    private String createTime;
}