package com.zhang.thresh.common.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * 用户信息
 * @author MrZhang
 */
@Data
@SuppressWarnings("all")
@EqualsAndHashCode(callSuper = true)
public class ThreshAuthUser extends User {

    private static final long serialVersionUID = -6411066541689297219L;

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
     * 用户角色ID
     */
    private String roleId;
    /**
     * 用户角色名称
     */
    private String roleName;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 备注
     */
    private String description;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 数据权限ID集合
     */
    private String deptIds;

    public ThreshAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ThreshAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
