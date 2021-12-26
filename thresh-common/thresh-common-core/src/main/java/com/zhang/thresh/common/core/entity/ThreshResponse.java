package com.zhang.thresh.common.core.entity;

import java.util.HashMap;

/**
 * Thresh响应类
 * @author MrZhang
 */
public class ThreshResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    /**
     * 插入信息
     * @param message 信息
     * @return
     */
    public ThreshResponse message(String message) {
        this.put("message", message);
        return this;
    }

    /**
     * 插入数据
     * @param data 数据
     * @return
     */
    public ThreshResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    /**
     * 插入一条自定义键值对
     * @param data 数据
     * @return
     */
    @Override
    public ThreshResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 获取信息
     * @param data 数据
     * @return
     */
    public String getMessage() {
        return String.valueOf(get("message"));
    }

    /**
     * 获取数据
     * @param data 数据
     * @return
     */
    public Object getData() {
        return get("data");
    }

    /**
     * 获取指定key的值
     * @param key 键
     * @return
     */
    public Object get(String key) {
        return super.get(key);
    }
}
