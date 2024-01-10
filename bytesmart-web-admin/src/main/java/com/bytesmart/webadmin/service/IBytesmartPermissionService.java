package com.bytesmart.webadmin.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;

import java.util.Set;

public interface IBytesmartPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param employeeId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(BytesmartEmployee employee);

    /**
     * 获取菜单数据权限
     *
     * @param employeeId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(BytesmartEmployee employee);
}
