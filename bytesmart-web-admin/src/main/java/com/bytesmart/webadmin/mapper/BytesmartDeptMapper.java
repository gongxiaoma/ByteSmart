package com.bytesmart.webadmin.mapper;

import com.bytesmart.apisystem.domain.BytesmartDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartDeptMapper {
    //查询所有
    public List<BytesmartDept> selectDeptList(BytesmartDept dept);

    //根据id查询
    public BytesmartDept selectDeptById(Long deptId);

    //修改
    public int updateDept(BytesmartDept dept);

    //增加
    public int insertDept(BytesmartDept dept);

    //删除
    public int deleteDeptById(Long deptId);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistEmployee(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public BytesmartDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);


    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 修改所在部门正常状态
     *
     * @param deptIds 部门ID组
     */
    public void updateDeptStatusNormal(Long[] deptIds);

    /**
     * 根据ID查询所有子部门
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<BytesmartDept> selectChildrenDeptById(Long deptId);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<BytesmartDept> depts);







}
