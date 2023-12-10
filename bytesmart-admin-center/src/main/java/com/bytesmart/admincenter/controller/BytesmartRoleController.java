package com.bytesmart.admincenter.controller;

import com.bytesmart.admincenter.service.IBytesmartDeptService;
import com.bytesmart.admincenter.service.IBytesmartEmployeeService;
import com.bytesmart.admincenter.service.IBytesmartRoleService;
import com.bytesmart.apisystem.domain.*;
import com.bytesmart.common.core.utils.poi.ExcelUtil;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/role")
public class BytesmartRoleController extends BaseController {

    @Autowired
    private IBytesmartRoleService bytesmartRoleService;

    @Autowired
    private IBytesmartEmployeeService bytesmartEmployeeService;

    @Autowired
    private IBytesmartDeptService bytesmartDeptService;



    @GetMapping("/list")
    public TableDataInfo list( BytesmartRole role)
    {
        startPage();
        List<BytesmartRole> list = bytesmartRoleService.selectRoleList(role);
        return getDataTable(list);
    }


    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BytesmartRole role)
    {
        List<BytesmartRole> list = bytesmartRoleService.selectRoleList(role);
        ExcelUtil<BytesmartRole> util = new ExcelUtil<BytesmartRole>(BytesmartRole.class);
        util.exportExcel(response, list, "角色数据");
    }



    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
//        bytesmartRoleService.checkRoleDataScope(roleId);
        return success(bytesmartRoleService.selectRoleById(roleId));
    }


//    简单的修改
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody BytesmartRole role){
//        return toAjax(bytesmartRoleService.updateRole(role));
//    }

    /**
     * 修改保存角色
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartRole role)
    {
        bytesmartRoleService.checkRoleAllowed(role);
        bytesmartRoleService.checkRoleDataScope(role.getRoleId());
        if (!bytesmartRoleService.checkRoleNameUnique(role))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!bytesmartRoleService.checkRoleKeyUnique(role))
        {
            return error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartRoleService.updateRole(role));
    }

    /**
     * 修改保存数据权限
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody BytesmartRole role)
    {
        bytesmartRoleService.checkRoleAllowed(role);
        bytesmartRoleService.checkRoleDataScope(role.getRoleId());
        return toAjax(bytesmartRoleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody BytesmartRole role)
    {
//        bytesmartRoleService.checkRoleAllowed(role);
//        bytesmartRoleService.checkRoleDataScope(role.getRoleId());
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartRoleService.updateRoleStatus(role));
    }



//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody BytesmartRole role){
//        return toAjax(bytesmartRoleService.insertRole(role));
//    }

    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartRole role)
    {
        if (!bytesmartRoleService.checkRoleNameUnique(role))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (!bytesmartRoleService.checkRoleKeyUnique(role))
        {
            return error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartRoleService.insertRole(role));
    }

    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds){
        return toAjax(bytesmartRoleService.deleteRoleByIds(roleIds));
    }

    //获取角色选择列表
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        return success(bytesmartRoleService.selectRoleAll());
    }

    /**
     * 查询已分配用户角色列表
     */

    @GetMapping("/authUser/allocatedList")
    public TableDataInfo allocatedList(BytesmartEmployee employee)
    {
        startPage();
        List<BytesmartEmployee> list = bytesmartEmployeeService.selectAllocatedList(employee);
        return getDataTable(list);
    }


    /**
     * 获取对应角色部门树列表
     */
    @GetMapping(value = "/deptTree/{roleId}")
    public AjaxResult deptTree(@PathVariable("roleId") Long roleId)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", bytesmartDeptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", bytesmartDeptService.selectDeptTreeList(new BytesmartDept()));
        return ajax;
    }


}
