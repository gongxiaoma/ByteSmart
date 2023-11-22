package com.bytesmart.admincenter.controller;


import com.bytesmart.admincenter.service.IBytesmartRoleService;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class BytesmartRoleController extends BaseController {

    @Autowired
    private IBytesmartRoleService bytesmartRoleService;

    @GetMapping("/list")
    public TableDataInfo list(BytesmartRole role)
    {
        startPage();
        List<BytesmartRole> list = bytesmartRoleService.selectRoleList(role);
        return getDataTable(list);
    }

    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable Integer roleId)
    {
        return success(bytesmartRoleService.selectRoleById(roleId));
    }

    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartRole role){
        return toAjax(bytesmartRoleService.updateRole(role));
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartRole role){
        return toAjax(bytesmartRoleService.insertRole(role));
    }

    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds){
        return toAjax(bytesmartRoleService.deleteRoleByIds(roleIds));
    }

}
