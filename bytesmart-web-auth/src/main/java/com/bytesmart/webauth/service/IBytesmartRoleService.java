package com.bytesmart.webauth.service;

import com.bytesmart.apisystem.domain.BytesmartRole;

import java.util.List;
import java.util.Set;

public interface IBytesmartRoleService {

    /**
     * 根据用户ID查询角色
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    public Set<String> selectRolePermissionByEmployeeId(Long employeeId);


    //所有角色列表
    public List<BytesmartRole> selectRoleAll();

    //查询所有
    public List<BytesmartRole> selectRoleList(BytesmartRole role);



}
