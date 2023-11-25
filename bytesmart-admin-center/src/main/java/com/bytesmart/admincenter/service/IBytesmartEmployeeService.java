package com.bytesmart.admincenter.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;

import java.util.List;

public interface IBytesmartEmployeeService {
    //查询所有
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee);

    //按id查询
    public BytesmartEmployee selectEmployeeById(Integer emloyeeId );

    //新增
    public int insertEmployee(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);




}
