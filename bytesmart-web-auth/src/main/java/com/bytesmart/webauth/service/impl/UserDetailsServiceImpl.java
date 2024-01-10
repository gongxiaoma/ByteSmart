package com.bytesmart.webauth.service.impl;


import com.bytesmart.common.core.enums.EmployeeStatus;
import com.bytesmart.webauth.domain.WebLoginUser;
import com.bytesmart.webadmin.mapper.BytesmartEmployeeMapper;
import com.bytesmart.webadmin.mapper.BytesmartMenuMapper;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        // 查询对应权限信息12.20新增
        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));

        // 这里有问题，会返回A granted authority textual representation is required
//        List<String> list = menuMapper.selectMenuPermsByEmployeeId(employee.getEmployeeId());
        return new WebLoginUser(employee, list);

    }
}
