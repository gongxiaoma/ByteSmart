package com.bytesmart.webauth.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartEmployeeMapper {

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

    //按username查询
    public BytesmartEmployee selectEmployeeByUsername(String userName);

}
