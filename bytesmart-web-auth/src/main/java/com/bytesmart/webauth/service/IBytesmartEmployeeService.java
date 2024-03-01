package com.bytesmart.webauth.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;

import java.util.List;

public interface IBytesmartEmployeeService {


    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

    //按userName查询
    public BytesmartEmployee selectEmployeeByUsername(String userName);



    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectEmployeePostGroup(String userName);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectEmployeeRoleGroup(String userName);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetEmployeerPwd(String userName, String password);

    /**
     * 校验手机号码是否唯一
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean checkPhoneUnique(BytesmartEmployee employee);

    /**
     * 校验email是否唯一
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean checkEmailUnique(BytesmartEmployee employee);

    /**
     * 修改用户基本信息
     *
     * @param employee 用户信息
     * @return 结果
     */
    public int updateEmployeeProfile(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);



}
