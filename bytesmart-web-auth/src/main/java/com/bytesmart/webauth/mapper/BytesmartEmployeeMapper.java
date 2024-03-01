package com.bytesmart.webauth.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BytesmartEmployeeMapper {


    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

    //按username查询
    public BytesmartEmployee selectEmployeeByUsername(String userName);



    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetEmployeerPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 校验手机号码是否唯一
     *
     * @param employeeMobile 手机号码
     * @return 结果
     */
    public BytesmartEmployee checkPhoneUnique(String employeeMobile);


    /**
     * 校验email是否唯一
     *
     * @param employeeEmail 用户邮箱
     * @return 结果
     */
    public BytesmartEmployee checkEmailUnique(String employeeEmail);

    //修改
    public int updateEmployee(BytesmartEmployee employee);




}
