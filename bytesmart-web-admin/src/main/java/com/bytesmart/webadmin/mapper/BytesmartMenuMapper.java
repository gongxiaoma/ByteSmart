package com.bytesmart.webadmin.mapper;

import com.bytesmart.webadmin.domain.BytesmartMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartMenuMapper {

    //查询系统菜单列表
    public List<BytesmartMenu> selectMenuList(BytesmartMenu menu);

    //根据用户所有权限
    public List<String> selectMenuPerms();

    //根据角色ID查询权限
    public List<BytesmartMenu> selectMenuListByEmployeeId(BytesmartMenu menu);


    public List<String> selectMenuPermsByRoleId(Long roleId);


    public List<String> selectMenuPermsByEmployeeId(Long employeeId);

    public List<BytesmartMenu> selectMenuTreeAll();

    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId);

    public List<Long> selectMenuListByRoleId(@Param("roleId") Long roleId, @Param("menuCheckStrictly") boolean menuCheckStrictly);

    public BytesmartMenu selectMenuById(Long menuId);

    public int hasChildByMenuId(Long menuId);

    public int insertMenu(BytesmartMenu menu);


    public int updateMenu(BytesmartMenu menu);


    public int deleteMenuById(Long menuId);

    public BytesmartMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);


}
