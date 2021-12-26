package com.zhang.thresh.common.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 当前用户信息
 * @author MrZhang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable {

    private static long serialVersionUID = 761748087824726463L;

    @JsonIgnore
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    @JsonIgnore
    /**
     * 权限
     */
    private Set<GrantedAuthority> authorities;
    /**
     * 账号是否过期
     */
    private boolean accountNonExpired;
    /**
     * 是否锁定
     */
    private boolean accountNonLocked;
    /**
     * 密码是否过期
     */
    private boolean credentialsNonExpired;
    /**
     * 账号是否可用
     */
    private boolean enabled;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户头像地址
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 所属部门ID
     */
    private Long deptId;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    @JsonIgnore
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 备注
     */
    private String description;
    /**
     * 状态
     */
    private String status;
    /**
     * 数据权限ID集合
     */
    private String deptIds;
}
