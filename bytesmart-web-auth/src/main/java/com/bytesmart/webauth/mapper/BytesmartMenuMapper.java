package com.bytesmart.webauth.mapper;

import com.bytesmart.webauth.domain.BytesmartMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface BytesmartMenuMapper {

//    //查询系统菜单列表
//    public List<BytesmartMenu> selectMenuList(BytesmartMenu menu);
//
//    //根据用户所有权限
//    public List<String> selectMenuPerms();
//
//    //根据角色ID查询权限
//    public List<BytesmartMenu> selectMenuListByEmployeeId(BytesmartMenu menu);
//
//
    public List<String> selectMenuPermsByRoleId(Long roleId);


    public Set<String> selectMenuPermsByEmployeeId(Long employeeId);


    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId);

    public List<BytesmartMenu> selectMenuTreeAll();




}
