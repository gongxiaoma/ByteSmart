package com.bytesmart.springsecurity.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.model.LoginEmployee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */


public class WebLoginUser extends LoginEmployee implements UserDetails
{

    /**
     * 无参构造
     */
    public WebLoginUser()
    {
    }

    public WebLoginUser(BytesmartEmployee employee, Set<String> permissions) {
        this.employee = employee;
        this.permissions = permissions;
    }


    //不被序列化(存储所需要的权限信息集合)
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    //spring security框架获取权限实际是调用的getAuthorities方法，在里面加工permissions权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(authorities!=null){
            return authorities;
        }
        //把permissions中string类型的权限信息封装成SimpleGrantedAuthority对象
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
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

}
