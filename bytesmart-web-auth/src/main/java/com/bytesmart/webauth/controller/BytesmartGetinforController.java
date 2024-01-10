package com.bytesmart.webauth.controller;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class BytesmartGetinforController extends BaseController {

    @Autowired
    private IBytesmartEmployeeService bytesmartEmployeeService;

    @Autowired
    private IBytesmartPermissionService bytesmartPermissionService;


    //      通过用户id获取用户信息
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        Long employeeId = 1L;
        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(employeeId);

//        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(WebSecurityUtils.getEmployeeId());
        // 角色集合
        Set<String> roles = bytesmartPermissionService.getRolePermission(bytesmartEmployee);
        // 权限集合
        Set<String> permissions = bytesmartPermissionService.getMenuPermission(bytesmartEmployee);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("bytesmartEmployee", bytesmartEmployee);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
