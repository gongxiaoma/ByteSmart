package com.bytesmart.admincenter.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysUser;

import java.util.List;

public interface BytesmartEmployeeMapper {
    //查询所有
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee);

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId );

    //按username查询
    public BytesmartEmployee selectEmployeeByUsername(String username);

    //新增
    public int insertEmployee(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);

    //删除
    public int deleteEmployeeByIds(Long[] employeeIds);

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    public List<BytesmartEmployee> selectAllocatedList(BytesmartEmployee employee);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    public List<BytesmartEmployee> selectUnallocatedList(BytesmartEmployee employee);

}
