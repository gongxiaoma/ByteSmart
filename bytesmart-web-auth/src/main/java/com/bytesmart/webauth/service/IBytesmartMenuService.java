package com.bytesmart.webauth.service;

import com.bytesmart.webauth.domain.BytesmartMenu;
import com.bytesmart.webauth.domain.vo.RouterVo;


import java.util.List;
import java.util.Set;

public interface IBytesmartMenuService {


//    //根据用户ID查询权限
    public Set<String> selectMenuPermsByEmployeeId(Long employeeId);
//
//    //根据角色ID查询权限
    public Set<String> selectMenuPermsByRoleId(Long roleId);

    //根据用户ID查询菜单树信息
    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId);

    //构建前端路由所需要的菜单
    public List<RouterVo> buildMenus(List<BytesmartMenu> menus);




}
