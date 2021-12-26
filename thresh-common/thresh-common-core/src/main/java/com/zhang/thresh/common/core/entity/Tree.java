package com.zhang.thresh.common.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树结构
 * @author MrZhang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree<T> {
    /**
     * 节点id
     */
    private String id;
    /**
     * 节点名称
     */
    private String label;
    /**
     * 子节点
     */
    private List<Tree<T>> children;
    /**
     * 父节点
     */
    private String parentId;
    /**
     * 是否有父节点
     */
    private boolean hasParent = false;
    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;

    /**
     * 初始化子节点
     */
    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
