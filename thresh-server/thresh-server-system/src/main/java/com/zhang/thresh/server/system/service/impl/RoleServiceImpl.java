package com.zhang.thresh.server.system.service.impl;

import com.zhang.thresh.common.core.entity.QueryRequest;
import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.common.core.entity.constant.StringConstant;
import com.zhang.thresh.common.core.entity.system.Role;
import com.zhang.thresh.common.core.entity.system.RoleMenu;
import com.zhang.thresh.common.core.utils.SortUtil;
import com.zhang.thresh.server.system.mapper.RoleMapper;
import com.zhang.thresh.server.system.service.IRoleMenuService;
import com.zhang.thresh.server.system.service.IRoleService;
import com.zhang.thresh.server.system.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author MrZhang
 */
@Slf4j
@Service("roleService")
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IRoleMenuService roleMenuService;
    private final IUserRoleService userRoleService;

    @Override
    public IPage<Role> findRoles(Role role, QueryRequest request) {
        Page<Role> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, ThreshConstant.CREATE_TIME, ThreshConstant.ORDER_DESC, false);
        return this.baseMapper.findRolePage(page, role);
    }

    @Override
    public List<Role> findUserRole(String userName) {
        return baseMapper.findUserRole(userName);
    }

    @Override
    public List<Role> findAllRoles() {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Role::getRoleId);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Role findByName(String roleName) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        role.setCreateTime(new Date());
        this.save(role);

        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(role.getMenuIds(), StringConstant.COMMA);
            setRoleMenus(role, menuIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String[] roleIds) {
        List<String> list = Arrays.asList(roleIds);
        baseMapper.deleteBatchIds(list);

        this.roleMenuService.deleteRoleMenusByRoleId(roleIds);
        this.userRoleService.deleteUserRolesByRoleId(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setRoleName(null);
        role.setModifyTime(new Date());
        baseMapper.updateById(role);

        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getRoleId()));
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(role.getMenuIds(), StringConstant.COMMA);
            setRoleMenus(role, menuIds);
        }
    }

    private void setRoleMenus(Role role, String[] menuIds) {
        List<RoleMenu> roleMenus = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            if (StringUtils.isNotBlank(menuId)) {
                roleMenu.setMenuId(Long.valueOf(menuId));
            }
            roleMenu.setRoleId(role.getRoleId());
            roleMenus.add(roleMenu);
        });
        this.roleMenuService.saveBatch(roleMenus);
    }

}
