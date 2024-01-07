package com.bytesmart.webauth.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */


public class WebLoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 封装自己用户模型
     */
    private BytesmartEmployee employee;

    /**
     * 用户唯一标识
     */
    private String userkey;

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

    //12.30注释
//    private Set<String> permissions;

    //12.30新增
    private List<String> permissions;

    //12.30新增
    public WebLoginUser(BytesmartEmployee employee, List<String> permissions) {
        this.employee = employee;
        this.permissions = permissions;
    }

    //不被序列化(存储所需要的权限信息集合)
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    //12.30新增
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(authorities!=null){
            return authorities;
        }
        //把permissions中string类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * 无参构造
     */
    public WebLoginUser()
    {
    }

    /**
     * 有参构造
     */
    //12.30注释
//    public WebLoginUser(BytesmartEmployee employee, Set<String> permissions)
//    {
//        this.employee = employee;
//        this.permissions = permissions;
//    }

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

    /**
     * 框架会调用WebLoginUser里面的getPassword方法获取密码，所以需要返回自己用户模型的密码
     */
    @JSONField(serialize = false)
    @Override
    public String getPassword()
    {
        return employee.getPassword();
    }

    /**
     * 框架会调用WebLoginUser里面的getUsername方法获取用户，所以需要返回自己用户模型的用户
     */
    @Override
    public String getUsername()
    {
        return employee.getUserName();
    }


    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
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

//    public Set<String> getPermissions()
//    {
//        return permissions;
//    }
//
//    public void setPermissions(Set<String> permissions)
//    {
//        this.permissions = permissions;
//    }

    public BytesmartEmployee getEmployee()
    {
        return employee;
    }

    public void setEmployee(BytesmartEmployee employee)
    {
        this.employee = employee;
    }


}
