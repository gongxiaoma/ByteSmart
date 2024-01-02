package com.bytesmart.webadmin.controller;

import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.domain.BytesmartMenu;
import com.bytesmart.webadmin.service.IBytesmartMenuService;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class BytesmartMenuController extends BaseController {

    @Autowired
    private IBytesmartMenuService bytesmartMenuService;

    //获取菜单列表
    @GetMapping("/list")
    public AjaxResult list(BytesmartMenu menu)
    {
//        Long employeeId = 1L;
        Long employeeId = WebSecurityUtils.getEmployeeId();
        List<BytesmartMenu> menus = bytesmartMenuService.selectMenuList(menu, employeeId);
        return success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId)
    {
        return success(bytesmartMenuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(BytesmartMenu menu)
    {
//        Long employeeId = 1L;
        Long employeeId = WebSecurityUtils.getEmployeeId();
        List<BytesmartMenu> menus = bytesmartMenuService.selectMenuList(menu, employeeId);
        return success(bytesmartMenuService.buildMenuTreeSelect(menus));
    }


    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
//        Long employeeId = 1L;
        Long employeeId = WebSecurityUtils.getEmployeeId();
        List<BytesmartMenu> menus = bytesmartMenuService.selectMenuList(employeeId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", bytesmartMenuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", bytesmartMenuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartMenu menu)
    {
        if (!bytesmartMenuService.checkMenuNameUnique(menu))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getRouteAddress()))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(WebSecurityUtils.getUsername());
        return toAjax(bytesmartMenuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartMenu menu)
    {
        if (!bytesmartMenuService.checkMenuNameUnique(menu))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getRouteAddress()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(WebSecurityUtils.getUsername());
        return toAjax(bytesmartMenuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (bytesmartMenuService.hasChildByMenuId(menuId))
        {
            return warn("存在子菜单,不允许删除");
        }
        if (bytesmartMenuService.checkMenuExistRole(menuId))
        {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(bytesmartMenuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
//        Long employeeId = 1L;
        Long employeeId = WebSecurityUtils.getEmployeeId();
        List<BytesmartMenu> menus = bytesmartMenuService.selectMenuTreeByEmployeeId(employeeId);
        return success(bytesmartMenuService.buildMenus(menus));
    }


}

