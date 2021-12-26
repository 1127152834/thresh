package com.zhang.thresh.common.core.entity;


import com.zhang.thresh.common.core.entity.system.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单树
 * @author MrZhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends Tree<Menu> {
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单所处文件目录
     */
    private String component;
    /**
     * 访问所需权限
     */
    private String perms;
    /**
     * 菜单Icon
     */
    private String icon;
    /**
     * 菜单类型
     */
    private String type;
    /**
     * 序号
     */
    private Integer orderNum;
}
