package com.zhang.thresh.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单关系表 多对多
 * @author MrZhang
 */
@TableName("t_role_menu")
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -7573904024872252113L;
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID")
    private Long roleId;
    /**
     * 菜单ID
     */
    @TableId(value = "MENU_ID")
    private Long menuId;
}
