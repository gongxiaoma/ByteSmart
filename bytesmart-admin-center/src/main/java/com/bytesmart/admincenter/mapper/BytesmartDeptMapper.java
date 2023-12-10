package com.bytesmart.admincenter.mapper;

import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.admincenter.domain.vo.TreeSelect;
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
    public int deleteDeptByIds(Long[] deptIds);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return 选中部门列表
     */
    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);








}
