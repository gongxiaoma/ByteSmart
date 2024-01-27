package com.bytesmart.webadmin.service;

import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.webadmin.domain.BytesmartEmployeeOnline;


/**
 * 在线用户 服务层
 *
 * @author hd
 */
public interface IBytesmartEmployeeOnlineService {

    /**
     * 通过登录地址查询信息
     *
     * @param ipaddr 登录地址
     * @param employee 用户信息
     * @return 在线用户信息
     */
    public BytesmartEmployeeOnline selectOnlineByIpaddr(String ipaddr, LoginEmployee employee);

    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param employee 用户信息
     * @return 在线用户信息
     */
    public BytesmartEmployeeOnline selectOnlineByUserName(String userName, LoginEmployee employee);

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param employee 用户信息
     * @return 在线用户信息
     */
    public BytesmartEmployeeOnline selectOnlineByInfo(String ipaddr, String userName, LoginEmployee employee);

    /**
     * 设置在线用户信息
     *
     * @param employee 用户信息
     * @return 在线用户
     */
    public BytesmartEmployeeOnline loginUserToUserOnline(LoginEmployee employee);
}
