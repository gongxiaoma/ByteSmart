package com.bytesmart.admincenter.service;

import com.bytesmart.admincenter.domain.vo.TreeSelect;
import com.bytesmart.apisystem.domain.BytesmartDept;
import com.bytesmart.apisystem.domain.SysDept;

import java.util.List;

public interface IBytesmartDeptService {
    //查询所有
    public List<BytesmartDept> selectDeptList(BytesmartDept dept);

    //根据id查询
    public BytesmartDept selectDeptById(Long deptId);

    //修改
    public int updateDept(BytesmartDept dept);

    //增加
    public int insertDept(BytesmartDept dept);

    //删除
    public int deleteDeptByIds(Long[] deptIds);



    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<BytesmartDept> depts);



    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    public List<BytesmartDept> buildDeptTree(List<BytesmartDept> depts);


    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    public List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 查询部门树结构信息
     *
     * @param dept 部门信息
     * @return 部门树信息集合
     */
    public List<TreeSelect> selectDeptTreeList(BytesmartDept dept);
}
