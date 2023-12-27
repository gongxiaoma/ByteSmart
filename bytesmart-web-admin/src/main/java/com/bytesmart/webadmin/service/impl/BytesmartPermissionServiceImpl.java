package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.webadmin.service.IBytesmartMenuService;
import com.bytesmart.webadmin.service.IBytesmartPermissionService;
import com.bytesmart.webadmin.service.IBytesmartRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户权限处理
 * 
 * @author hd
 */
@Service
public class BytesmartPermissionServiceImpl implements IBytesmartPermissionService
{
    @Autowired
    private IBytesmartRoleService roleService;

    @Autowired
    private IBytesmartMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param employeeId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(BytesmartEmployee bytesmartEmployee)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (bytesmartEmployee.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByEmployeeId(bytesmartEmployee.getEmployeeId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param employeeId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(BytesmartEmployee bytesmartEmployee)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (bytesmartEmployee.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            List<BytesmartRole> roles = bytesmartEmployee.getRoles();
            if (!CollectionUtils.isEmpty(roles))
            {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (BytesmartRole role : roles)
                {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            }
            else
            {
                perms.addAll(menuService.selectMenuPermsByEmployeeId(bytesmartEmployee.getEmployeeId()));
            }
        }
        return perms;
    }
}
