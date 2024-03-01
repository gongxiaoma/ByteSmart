package com.bytesmart.webauth.service.impl;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;

import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webauth.mapper.BytesmartRoleMapper;
import com.bytesmart.webauth.service.IBytesmartRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BytesmartRoleServiceImpl implements IBytesmartRoleService {

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    //根据用户ID查询权限
    @Override
    public Set<String> selectRolePermissionByEmployeeId(Long employeeId)
    {
        List<BytesmartRole> perms = bytesmartRoleMapper.selectRolePermissionByEmployeeId(employeeId);


        Set<String> permsSet = new HashSet<>();
        for (BytesmartRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<BytesmartRole> selectRoleList(BytesmartRole role){
        return bytesmartRoleMapper.selectRoleList(role);
    }


    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<BytesmartRole> selectRoleAll(){
        return SpringUtils.getAopProxy(this).selectRoleList(new BytesmartRole());

    }



}
