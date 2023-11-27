package com.bytesmart.admincenter.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysUser;

import java.util.List;

public interface BytesmartEmployeeMapper {
    //查询所有
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee);

    //按id查询
    public BytesmartEmployee selectEmployeeById(Integer emloyeeId );

    //新增
    public int insertEmployee(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);

    //删除
    public int deleteEmployeeByIds(Integer[] employeeIds);

}
