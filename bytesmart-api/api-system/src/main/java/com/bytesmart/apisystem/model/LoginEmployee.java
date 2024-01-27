package com.bytesmart.apisystem.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.bytesmart.apisystem.domain.BytesmartEmployee;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */


public class LoginEmployee
{
    private static final long serialVersionUID = 1L;

    /**
     * 封装自己用户模型
     */
    public BytesmartEmployee employee;

    /**
     * 用户唯一标识
     */
    private String userkey;

    /**
     * 用户名id
     */
    private Long userid;


    /**
     * 用户名
     */
    private String username;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */


    //12.30新增
    public Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;

    //12.30新增
    public LoginEmployee(BytesmartEmployee employee, Set<String> permissions) {
        this.employee = employee;
        this.permissions = permissions;
    }


    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * 无参构造
     */
    public LoginEmployee()
    {
    }

    /**
     * 有参构造
     */


    /**
     * 获取自己用户模型的用户id
     */
    public Long EmployeeId()
    {
        return employee.getEmployeeId();
    }

    /**
     * 获取自己用户模型的部门id
     */
    public Long getDeptId()
    {
        return employee.getDeptId();
    }

    public String getUserkey()
    {
        return userkey;
    }

    public void setUserkey(String userkey)
    {
        this.userkey = userkey;
    }

    public Long getUserid()
    {
        return employee.getEmployeeId();
    }

    public void setUserid(Long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<String> roles)
    {
        this.roles = roles;
    }

    public BytesmartEmployee getEmployee()
    {
        return employee;
    }

    public void setEmployee(BytesmartEmployee employee)
    {
        this.employee = employee;
    }


}
