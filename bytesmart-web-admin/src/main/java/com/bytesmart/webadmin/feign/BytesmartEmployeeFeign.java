package com.bytesmart.webadmin.feign;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.apisystem.model.LoginUser;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.security.annotation.InnerAuth;
import com.bytesmart.webadmin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userinfo")
public class BytesmartEmployeeFeign extends BaseController {

    @Autowired
    private IBytesmartEmployeeService bytesmartEmployeeService;

    @Autowired
    private IBytesmartRoleService bytesmartRoleService;

    @Autowired
    private IBytesmartPostService bytesmartPostService;

    @Autowired
    private IBytesmartDeptService bytesmartDeptService;

    @Autowired
    private IBytesmartPermissionService bytesmartPermissionService;

    @Autowired
    private IBytesmartConfigService bytesmartConfigService;


    @InnerAuth
    @GetMapping("/{employeeId}")
    public R<LoginEmployee> getEmployeeInfoById(@PathVariable("employeeId") Long employeeId)
    {
        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(employeeId);
        if (StringUtils.isNull(bytesmartEmployee))
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = bytesmartPermissionService.getRolePermission(bytesmartEmployee);
        // 权限集合
        Set<String> permissions = bytesmartPermissionService.getMenuPermission(bytesmartEmployee);

        LoginEmployee bytesmartEmployeeVo = new LoginEmployee();
        bytesmartEmployeeVo.setEmployee(bytesmartEmployee);
        bytesmartEmployeeVo.setRoles(roles);
        bytesmartEmployeeVo.setPermissions(permissions);
        return R.ok(bytesmartEmployeeVo);
    }


    //获取当前用户信息（通过用户名）
    @InnerAuth
    @GetMapping("/info/{userName}")
    public R<LoginEmployee> info(@PathVariable("userName") String userName)
    {
        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeByUsername(userName);
        if (StringUtils.isNull(bytesmartEmployee))
        {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = bytesmartPermissionService.getRolePermission(bytesmartEmployee);
        // 权限集合
        Set<String> permissions = bytesmartPermissionService.getMenuPermission(bytesmartEmployee);

        LoginEmployee bytesmartEmployeeVo = new LoginEmployee();
        bytesmartEmployeeVo.setEmployee(bytesmartEmployee);
        bytesmartEmployeeVo.setRoles(roles);
        bytesmartEmployeeVo.setPermissions(permissions);
        return R.ok(bytesmartEmployeeVo);
    }
}
