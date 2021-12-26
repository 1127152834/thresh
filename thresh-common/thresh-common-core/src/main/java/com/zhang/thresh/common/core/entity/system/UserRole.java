package com.zhang.thresh.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户-角色关系表 多对多
 * @author MrZhang
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -3166012934498268403L;
    /**
     * 用户ID
     */
    @TableId(value = "USER_ID")
    private Long userId;
    /**
     * 角色ID
     */
    @TableId(value = "ROLE_ID")
    private Long roleId;

}
