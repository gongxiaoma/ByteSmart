package com.bytesmart.webadmin.controller;




import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.annotation.RequiresPermissions;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.service.IBytesmartDeptService;
import com.bytesmart.apisystem.domain.BytesmartDept;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class BytesmartDeptController extends BaseController {

    @Autowired
    private IBytesmartDeptService bytesmartDeptService;

    //获取部门列表
    //@RequiresPermissions("webadmin:dept:list ")
    @GetMapping("/list")
    public AjaxResult list(BytesmartDept dept)
    {
        List<BytesmartDept> list = bytesmartDeptService.selectDeptList(dept);
        return success(list);
    }

    //查询部门列表（排除节点）
    @RequiresPermissions("system:dept:list")
    @GetMapping("/list/exclude/{deptId}")
    public AjaxResult excludeChild(@PathVariable(value = "deptId", required = false) Long deptId)
    {
        List<BytesmartDept> depts = bytesmartDeptService.selectDeptList(new BytesmartDept());
        depts.removeIf(d -> d.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""));
        return success(depts);
    }

    //根据部门编号获取详细信息
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId)
    {
        return success(bytesmartDeptService.selectDeptById(deptId));
    }

//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody BytesmartDept dept){
//        return toAjax(bytesmartDeptService.updateDept(dept));
//    }

    //新增
    //@RequiresPermissions("system:dept:add")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartDept dept)
    {
        if (!bytesmartDeptService.checkDeptNameUnique(dept))
        {
            return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartDeptService.insertDept(dept));
    }


    //修改部门
    //@RequiresPermissions("webadmin:dept:edit")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartDept dept)
    {
        Long deptId = dept.getDeptId();
        bytesmartDeptService.checkDeptDataScope(deptId);
        if (!bytesmartDeptService.checkDeptNameUnique(dept))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(deptId))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getDeptStatus()) && bytesmartDeptService.selectNormalChildrenDeptById(deptId) > 0)
        {
            return error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(WebSecurityUtils.getUsername());
        return toAjax(bytesmartDeptService.updateDept(dept));
    }



    //删除部门
    //    @RequiresPermissions("webadmin:dept:remove")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
        if (bytesmartDeptService.hasChildByDeptId(deptId))
        {
            return warn("存在下级部门,不允许删除");
        }
        if (bytesmartDeptService.checkDeptExistEmployee(deptId))
        {
            return warn("部门存在用户,不允许删除");
        }
        bytesmartDeptService.checkDeptDataScope(deptId);
        return toAjax(bytesmartDeptService.deleteDeptById(deptId));

    }





}
