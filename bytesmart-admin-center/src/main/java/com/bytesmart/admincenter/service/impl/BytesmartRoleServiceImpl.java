package com.bytesmart.admincenter.service.impl;

import com.bytesmart.admincenter.mapper.BytesmartRoleMapper;
import com.bytesmart.admincenter.service.IBytesmartRoleService;
import com.bytesmart.apisystem.domain.BytesmartRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartRoleServiceImpl implements IBytesmartRoleService {

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    @Override
    public List<BytesmartRole> selectRoleList(BytesmartRole role){
        return bytesmartRoleMapper.selectRoleList(role);
    }

    @Override
    public BytesmartRole selectRoleById(Integer roleId){
        return bytesmartRoleMapper.selectRoleById(roleId);
    }

    @Override
    public int updateRole(BytesmartRole role){
        return bytesmartRoleMapper.updateRole(role);
    }

    @Override
    public int insertRole(BytesmartRole role){
        return bytesmartRoleMapper.insertRole(role);
    }

    public int deleteRoleByIds(Long[] roleIds){
        return bytesmartRoleMapper.deleteRoleByIds(roleIds);
    }

}
