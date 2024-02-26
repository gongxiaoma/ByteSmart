package com.bytesmart.systemcenter.controller;

import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.annotation.RequiresPermissions;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.systemcenter.domain.SysMenu;
import com.bytesmart.systemcenter.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 * 
 * @author hd
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
//    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu)
      //定义一个名字为list的方法，这个方法的返回数据类型为AjaxResult,且需要定义形参menu.
    {
        //调用安全类的etUserId()方法，返回一个long类型的对象，用变量userId去接它。
        Long userId = SecurityUtils.getUserId();
        //调用menuService类的selectMenuList方法，这个方法需要传递实参即menu,userId,接着返回一个list集合类型的对象，用变量menus去接它。
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);

        //最后返回调用success方法，这个方法的返回数据类型AjaxResult，也需要实参menus传递进去。
        return success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
//    @RequiresPermissions("system:menu:query")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId)
    {
        return success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenu menu)
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, userId);
        return success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        //定义一个方法roleMenuTreeselect，返回数据类型是AjaxResult，需要定义一个形参roleId，形参的数据类型是Long

        //调用安全类的方法getUserId，该方法返回一个数据类型为long的对象，我们用变量userId去接它。
        Long userId = SecurityUtils.getUserId();
        //调用类menuService的方法selectMenuList，该方法需要传递实参userId，然后返回一个数据类型为List<SysMenu>集合的对象，我们用变量menus去接它。
        List<SysMenu> menus = menuService.selectMenuList(userId);
        //调用类AjaxResult的方法success，该方法一个返回数据类型为AjaxResult的对象，我们用变量ajax去接它。
        AjaxResult ajax = AjaxResult.success();
        //调用对象ajax的put方法，传递实参：即调用类menuService的方法selectMenuListByRoleId，该方法需要传递实参(roleId)
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        //调用对象ajax的put方法，传递实参：即调用类menuService的方法buildMenuTreeSelect，该方法需要传递实参(menus)
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        //最后返回对象ajax
        return ajax;
    }

    /**
     * 新增菜单
     */
//    @RequiresPermissions("system:menu:add")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu)
    {
        if (!menuService.checkMenuNameUnique(menu))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
//    @RequiresPermissions("system:menu:edit")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (!menuService.checkMenuNameUnique(menu))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
//    @RequiresPermissions("system:menu:remove")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.hasChildByMenuId(menuId))
        {
            return warn("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId))
        {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = 1L;
//        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return success(menuService.buildMenus(menus));
    }
}
