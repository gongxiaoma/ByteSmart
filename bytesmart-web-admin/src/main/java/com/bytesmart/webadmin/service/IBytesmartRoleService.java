package com.bytesmart.webadmin.service;

import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.webadmin.domain.BytesmartEmployeeRole;

import java.util.List;
import java.util.Set;

public interface IBytesmartRoleService {
    //查询所有
    public List<BytesmartRole> selectRoleList(BytesmartRole role);

    //按id查询
    public BytesmartRole selectRoleById(Long roleId);

    //修改
    public int updateRole(BytesmartRole role);

    //新增
    public int insertRole(BytesmartRole role);

    //删除
    public int deleteRoleById(Long roleId);

    //批量删除
    public int deleteRoleByIds(Long[] roleIds);

    //所有角色列表
    public List<BytesmartRole> selectRoleAll();


    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public boolean checkRoleNameUnique(BytesmartRole role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    public boolean checkRoleKeyUnique(BytesmartRole role);

    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    public void checkRoleAllowed(BytesmartRole role);

    /**
     * 校验角色是否有数据权限
     *
     * @param roleId 角色id
     */
    public void checkRoleDataScope(Long roleId);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    public int countemployeeRoleByRoleId(Long roleId);



    /**
     * 根据用户ID查询角色
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    public Set<String> selectRolePermissionByEmployeeId(Long employeeId);

    /**
     * 根据用户ID查询角色列表
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    public List<BytesmartRole> selectRolesByEmployeeId(Long employeeId);


    /**
     * 取消授权用户角色
     *
     * @param employeeRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteAuthEmployee(BytesmartEmployeeRole employeeRole);

    /**
     * 批量取消授权用户角色
     *
     * @param roleId 角色ID
     * @param employeeIds 需要取消授权的用户数据ID
     * @return 结果
     */
    public int deleteAuthEmployees(Long roleId, Long[] employeeIds);

    /**
     * 批量选择授权用户角色
     *
     * @param roleId 角色ID
     * @param employeeIds 需要删除的用户数据ID
     * @return 结果
     */
    public int insertAuthEmployees(Long roleId, Long[] employeeIds);


    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    public int updateRoleStatus(BytesmartRole role);


    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    public int authDataScope(BytesmartRole role);



}
