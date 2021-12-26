package com.zhang.thresh.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户数据权限关系表 多对多
 * @author MrZhang
 */
@Data
@TableName("t_user_data_permission")
public class UserDataPermission {
    /**
     * 用户ID
     */
    @TableId("USER_ID")
    private Long userId;
    /**
     * 部门ID
     */
    @TableId("DEPT_ID")
    private Long deptId;

}
