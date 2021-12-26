package com.zhang.thresh.gateway.enhance.utils;


import com.zhang.thresh.common.core.entity.constant.ThreshConstant;

/**
 * 路由增强缓存工具类
 * @author MrZhang
 */
public abstract class RouteEnhanceCacheUtil {

    private static final String BLACKLIST_CHACHE_KEY_PREFIX = "thresh:route:blacklist:";
    private static final String RATELIMIT_CACHE_KEY_PREFIX = "thresh:route:ratelimit:";
    private static final String RATELIMIT_COUNT_KEY_PREFIX = "thresh:route:ratelimit:cout:";

    public static String getBlackListCacheKey(String ip) {
        if (ThreshConstant.LOCALHOST.equalsIgnoreCase(ip)) {
            ip = ThreshConstant.LOCALHOST_IP;
        }
        return String.format("%s%s", BLACKLIST_CHACHE_KEY_PREFIX, ip);
    }

    public static String getBlackListCacheKey() {
        return String.format("%sall", BLACKLIST_CHACHE_KEY_PREFIX);
    }

    public static String getRateLimitCacheKey(String uri, String method) {
        return String.format("%s%s:%s", RATELIMIT_CACHE_KEY_PREFIX, uri, method);
    }

    public static String getRateLimitCountKey(String uri, String ip) {
        return String.format("%s%s:%s", RATELIMIT_COUNT_KEY_PREFIX, uri, ip);
    }
}
