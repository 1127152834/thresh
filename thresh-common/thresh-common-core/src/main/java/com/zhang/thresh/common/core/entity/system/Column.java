package com.zhang.thresh.common.core.entity.system;

import lombok.Data;

/**
 * 数据库字段信息
 * @author MrZhang
 */
@Data
public class Column {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为主键
     */
    private Boolean isKey;
    /**
     * 类型
     */
    private String type;
    /**
     * 注释
     */
    private String remark;
    /**
     * 属性名称
     */
    private String field;
}
