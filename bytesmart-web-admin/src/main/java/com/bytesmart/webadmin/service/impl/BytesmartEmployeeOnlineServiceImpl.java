package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.webadmin.domain.BytesmartEmployeeOnline;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webadmin.service.IBytesmartEmployeeOnlineService;
import org.springframework.stereotype.Service;


@Service
public class BytesmartEmployeeOnlineServiceImpl implements IBytesmartEmployeeOnlineService {

    /**
     * 通过登录地址查询信息
     *
     * @param ipaddr 登录地址
     * @param employee 用户信息
     * @return 在线用户信息
     */
    @Override
    public BytesmartEmployeeOnline selectOnlineByIpaddr(String ipaddr, LoginEmployee employee)
    {
        if (StringUtils.equals(ipaddr, employee.getIpaddr()))
        {
            return loginUserToUserOnline(employee);
        }
        return null;
    }

    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param employee 用户信息
     * @return 在线用户信息
     */
    @Override
    public BytesmartEmployeeOnline selectOnlineByUserName(String userName, LoginEmployee employee)
    {
        if (StringUtils.equals(userName, employee.getUsername()))
        {
            return loginUserToUserOnline(employee);
        }
        return null;
    }

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param employee 用户信息
     * @return 在线用户信息
     */
    @Override
    public BytesmartEmployeeOnline selectOnlineByInfo(String ipaddr, String userName, LoginEmployee employee)

    {
        if (StringUtils.equals(ipaddr, employee.getIpaddr()) && StringUtils.equals(userName, employee.getUsername()))
        {
            return loginUserToUserOnline(employee);
        }
        return null;
    }

    /**
     * 设置在线用户信息
     *
     * @param employee 用户信息
     * @return 在线用户
     */
    @Override
    public BytesmartEmployeeOnline loginUserToUserOnline(LoginEmployee employee)
    {
        if (StringUtils.isNull(employee))
        {
            return null;
        }
        BytesmartEmployeeOnline bytesmartEmployeeOnline = new BytesmartEmployeeOnline();
        bytesmartEmployeeOnline.setTokenId(employee.getUserkey());
        bytesmartEmployeeOnline.setUserName(employee.getUsername());
        bytesmartEmployeeOnline.setIpaddr(employee.getIpaddr());
        bytesmartEmployeeOnline.setLoginTime(employee.getLoginTime());
        return bytesmartEmployeeOnline;
    }
}
