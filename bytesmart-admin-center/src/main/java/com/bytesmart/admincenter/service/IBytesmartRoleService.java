package com.bytesmart.admincenter.service;

import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.apisystem.domain.BytesmartRole;

import java.util.List;

public interface IBytesmartRoleService {
    //查询所有
    public List<BytesmartRole> selectRoleList(BytesmartRole role);

    //按id查询
    public BytesmartRole selectRoleById(Integer roleId);

    //修改
    public int updateRole(BytesmartRole role);

    //新增
    public int insertRole(BytesmartRole role);

    //删除
    public int deleteRoleByIds(Long[] roleIds);
}
