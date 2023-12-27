package com.bytesmart.webadmin.mapper;

import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.apisystem.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface BytesmartRoleMapper {
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

    public BytesmartRole selectRoleAll();


    /**
     * 根据用户ID查询角色
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    public List<BytesmartRole> selectRolePermissionByEmployeeId(Long employeeId);

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param employeeId 用户ID
     * @return 选中角色ID列表
     */
    public List<Long> selectRoleListByEmployeeId(Long employeeId);

    /**
     * 根据用户ID查询角色
     *
     * @param userName 用户名
     * @return 角色列表
     */
    public List<BytesmartRole> selectRolesByUserName(String userName);

    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色名称
     * @return 角色信息
     */
    public BytesmartRole checkRoleNameUnique(String role);

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色权限
     * @return 角色信息
     */
    public BytesmartRole checkRoleKeyUnique(String role);






}
