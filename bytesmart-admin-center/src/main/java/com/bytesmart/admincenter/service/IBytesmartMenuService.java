package com.bytesmart.admincenter.service;

import com.bytesmart.admincenter.domain.BytesmartMenu;
import com.bytesmart.admincenter.domain.vo.RouterVo;
import com.bytesmart.admincenter.domain.vo.TreeSelect;

import java.util.List;
import java.util.Set;

public interface IBytesmartMenuService {

    //根据用户查询系统菜单列表
    public List<BytesmartMenu> selectMenuList(Long employeeId);

    //根据用户查询系统菜单列表
    public List<BytesmartMenu> selectMenuList(BytesmartMenu menu, Long employeeId);

    //根据用户ID查询权限
    public Set<String> selectMenuPermsByEmployeeId(Long employeeId);

    //根据角色ID查询权限
    public Set<String> selectMenuPermsByRoleId(Long roleId);

    //根据用户ID查询菜单树信息
    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId);

    //根据角色ID查询菜单树信息
    public List<Long> selectMenuListByRoleId(Long roleId);

    //构建前端路由所需要的菜单
    public List<RouterVo> buildMenus(List<BytesmartMenu> menus);

    //构建前端所需要树结构
    public List<BytesmartMenu> buildMenuTree(List<BytesmartMenu> menus);

    //构建前端所需要下拉树结构
    public List<TreeSelect> buildMenuTreeSelect(List<BytesmartMenu> menus);

    //根据菜单ID查询信息
    public BytesmartMenu selectMenuById(Long menuId);

    //根据菜单ID查询信息
    public boolean hasChildByMenuId(Long menuId);

    //查询菜单是否存在角色
    public boolean checkMenuExistRole(Long menuId);

    //查询菜单是否存在角色
    public int insertMenu(BytesmartMenu menu);

    //修改保存菜单信息
    public int updateMenu(BytesmartMenu menu);

    //删除菜单管理信息
    public int deleteMenuById(Long menuId);

    //校验菜单名称是否唯一
    public boolean checkMenuNameUnique(BytesmartMenu menu);


}
