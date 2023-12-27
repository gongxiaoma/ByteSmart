package com.bytesmart.webadmin.controller;

import com.bytesmart.apisystem.domain.*;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.apisystem.model.LoginUser;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.security.annotation.InnerAuth;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.service.*;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/employee")
public class BytesmartEmployeeController extends BaseController {

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


    @GetMapping("/list")
    public TableDataInfo list(BytesmartEmployee employee)
    {
        startPage();
        List<BytesmartEmployee> list = bytesmartEmployeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    //根据用户编号获取详细信息
    @GetMapping(value = { "/", "/{employeeId}" })
    public AjaxResult getInfo(@PathVariable(value = "employeeId", required = false) Long employeeId)
    {
        //校验用户是否有数据权限
        bytesmartEmployeeService.checkEmployeeDataScope(employeeId);
        AjaxResult ajax = AjaxResult.success();
        List<BytesmartRole> roles = bytesmartRoleService.selectRoleAll();
        ajax.put("roles", BytesmartEmployee.isAdmin(employeeId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", bytesmartPostService.selectPostAll());
        if (StringUtils.isNotNull(employeeId))
        {
            BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(employeeId);
            ajax.put(AjaxResult.DATA_TAG, bytesmartEmployee);
            ajax.put("postIds", bytesmartPostService.selectPostListByEmployeeId(employeeId));
            ajax.put("roleIds", bytesmartEmployee.getRoles().stream().map(BytesmartRole::getRoleId).collect(Collectors.toList()));
        }
        return ajax;

//        return success(bytesmartEmployeeService.selectEmployeeById(employeeId));
    }



    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartEmployee employee){
        return toAjax(bytesmartEmployeeService.insertEmployee(employee));
    }


    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartEmployee employee){
        return toAjax(bytesmartEmployeeService.updateEmployee(employee));
    }

    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(bytesmartEmployeeService.deleteEmployeeByIds(employeeIds));
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
        bytesmartEmployeeVo.setBytesmartEmployee(bytesmartEmployee);
        bytesmartEmployeeVo.setRoles(roles);
        bytesmartEmployeeVo.setPermissions(permissions);
        return R.ok(bytesmartEmployeeVo);
    }

     // 通过用户id获取用户信息
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(WebSecurityUtils.getEmployeeId());
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


    //获取部门树列表
    @GetMapping("/deptTree")
    public AjaxResult deptTree(BytesmartDept dept)
    {
        return success(bytesmartDeptService.selectDeptTreeList(dept));
    }



    /**
     * 注册用户信息
     */
    @InnerAuth
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody BytesmartEmployee bytesmartEmployee)
    {
        String userName = bytesmartEmployee.getUserName();
        if (!("true".equals(bytesmartConfigService.selectConfigByKey("sys.account.registerUser"))))
        {
            return R.fail("当前系统没有开启注册功能！");
        }
        if (!bytesmartEmployeeService.checkUserNameUnique(bytesmartEmployee))
        {
            return R.fail("保存用户'" + userName + "'失败，注册账号已存在");
        }
        return R.ok(bytesmartEmployeeService.registerEmployee(bytesmartEmployee));
    }


}
