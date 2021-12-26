package com.zhang.thresh.common.core.entity.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建 Vue路由
 *
 * @author MrZhang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {

    private static final long serialVersionUID = -3327478146308500708L;
    /**
     * 路由ID
     */
    @JsonIgnore
    private String id;
    /**
     * 父路由ID
     */
    @JsonIgnore
    private String parentId;
    /**
     * 路由url
     */
    private String path;
    /**
     * 路由名称
     */
    private String name;
    /**
     * 组件所在目录
     */
    private String component;
    /**
     * 跳转地址
     */
    private String redirect;
    /**
     * 路由的元信息
     */
    private RouterMeta meta;
    /**
     * 是否隐藏
     */
    private Boolean hidden = false;
    /**
     * 是否总是显示
     */
    private Boolean alwaysShow = false;
    /**
     * 子路由
     */
    private List<VueRouter<T>> children;
    /**
     * 是否有父级
     */
    @JsonIgnore
    private Boolean hasParent = false;
    /**
     * 是否有子级
     */
    @JsonIgnore
    private Boolean hasChildren = false;
    /**
     * 初始化路由的子级
     */
    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
