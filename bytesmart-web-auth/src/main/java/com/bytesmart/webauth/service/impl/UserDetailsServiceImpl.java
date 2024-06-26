package com.bytesmart.webauth.service.impl;


import com.bytesmart.common.core.enums.EmployeeStatus;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webauth.mapper.BytesmartEmployeeMapper;
import com.bytesmart.webauth.mapper.BytesmartMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private BytesmartEmployeeMapper employeeMapper;

    @Autowired
    private BytesmartMenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        // 查询用户信息
        // TODO 新建根据username查询用户的方法
        BytesmartEmployee employee = employeeMapper.selectEmployeeByUsername(username);
        if (StringUtils.isNull(employee))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户不存在.");
        }
        else if (EmployeeStatus.DELETED.getCode().equals(employee.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("登录用户已被删除");

        }
        else if (EmployeeStatus.DISABLE.getCode().equals(employee.getEmployeeStatus()))
        {

            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("登录用户已被停用");
        }

        List<String> perms = menuMapper.selectMenuPermsByEmployeeId(employee.getEmployeeId());
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return new WebLoginUser(employee, permsSet);

    }
}
