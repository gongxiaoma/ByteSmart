package com.bytesmart.webauth.controller;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;
import com.bytesmart.webauth.service.IBytesmartEmployeeService;
import com.bytesmart.webauth.service.IBytesmartPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        BytesmartEmployee bytesrtEmployee = bytesmartEmployeeService.selectEmployeeById(WebSecurityUtils.getUserId());
        // 角色集合
        Set<String> roles = bytesmartPermissionService.getRolePermission(bytesrtEmployee);
        // 权限集合
        Set<String> permissions = bytesmartPermissionService.getMenuPermission(bytesrtEmployee);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("bytesmartEmployee", bytesrtEmployee);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
