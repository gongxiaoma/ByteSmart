package com.bytesmart.webauth.mapper;

import com.bytesmart.apisystem.domain.BytesmartRole;

import java.util.List;

public interface BytesmartRoleMapper {


    /**
     * 根据用户ID查询角色
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    public List<BytesmartRole> selectRolePermissionByEmployeeId(Long employeeId);

    public BytesmartRole selectRoleAll();

    //查询所有
    public List<BytesmartRole> selectRoleList(BytesmartRole role);

    /**
     * 根据用户ID查询角色
     *
     * @param userName 用户名
     * @return 角色列表
     */
    public List<BytesmartRole> selectRolesByUserName(String userName);



}
