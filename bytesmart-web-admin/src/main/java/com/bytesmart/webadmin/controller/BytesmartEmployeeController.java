package com.bytesmart.webadmin.controller;

import com.bytesmart.apisystem.domain.*;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.poi.ExcelUtil;
import com.bytesmart.common.security.annotation.InnerAuth;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.webadmin.service.*;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    //    @RequiresPermissions("webadmin:emloyee:list ")
    @GetMapping("/list")
    public TableDataInfo list(BytesmartEmployee employee)
    {
        startPage();
        List<BytesmartEmployee> list = bytesmartEmployeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("webadmin:emloyee:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BytesmartEmployee employee)
    {
        List<BytesmartEmployee> list = bytesmartEmployeeService.selectEmployeeList(employee);
        ExcelUtil<BytesmartEmployee> util = new ExcelUtil<BytesmartEmployee>(BytesmartEmployee.class);
        util.exportExcel(response, list, "用户数据");
    }

    //    @RequiresPermissions("webadmin:emloyee:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<BytesmartEmployee> util = new ExcelUtil<BytesmartEmployee>(BytesmartEmployee.class);
        List<BytesmartEmployee> employeeList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = bytesmartEmployeeService.importEmployee(employeeList, updateSupport, operName);
        return success(message);
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException
    {
        ExcelUtil<BytesmartEmployee> util = new ExcelUtil<BytesmartEmployee>(BytesmartEmployee.class);
        util.importTemplateExcel(response, "用户数据");
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

//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody BytesmartEmployee employee){
//        return toAjax(bytesmartEmployeeService.insertEmployee(employee));
//    }

    //    新增
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartEmployee employee)
    {
        if (!bytesmartEmployeeService.checkUserNameUnique(employee))
        {
            return error("新增用户'" + employee.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(employee.getEmployeeMobile()) && !bytesmartEmployeeService.checkPhoneUnique(employee))
        {
            return error("新增用户'" + employee.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(employee.getEmployeeEmail()) && !bytesmartEmployeeService.checkEmailUnique(employee))
        {
            return error("新增用户'" + employee.getUserName() + "'失败，邮箱账号已存在");
        }
        employee.setCreateBy(SecurityUtils.getUsername());
        employee.setPassword(SecurityUtils.encryptPassword(employee.getPassword()));
        return toAjax(bytesmartEmployeeService.insertEmployee(employee));
    }


//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody BytesmartEmployee employee){
//        return toAjax(bytesmartEmployeeService.updateEmployee(employee));
//    }

    //    修改
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartEmployee employee)
    {
        bytesmartEmployeeService.checkEmployeeAllowed(employee);
        bytesmartEmployeeService.checkEmployeeDataScope(employee.getEmployeeId());
        if (!bytesmartEmployeeService.checkUserNameUnique(employee))
        {
            return error("修改用户'" + employee.getUserName() + "'失败，登录账号已存在");
        }
        else if (StringUtils.isNotEmpty(employee.getEmployeeMobile()) && !bytesmartEmployeeService.checkPhoneUnique(employee))
        {
            return error("修改用户'" + employee.getUserName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(employee.getEmployeeEmail()) && !bytesmartEmployeeService.checkEmailUnique(employee))
        {
            return error("修改用户'" + employee.getUserName() + "'失败，邮箱账号已存在");
        }
        employee.setUpdateBy(SecurityUtils.getUsername());

        return toAjax(bytesmartEmployeeService.updateEmployee(employee));
    }

    //    @RequiresPermissions("webadmin:emloyee:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        if (ArrayUtils.contains(employeeIds, SecurityUtils.getUserId()))
        {
            return error("当前用户不能删除");
        }
        return toAjax(bytesmartEmployeeService.deleteEmployeeByIds(employeeIds));
    }

    /**
     * 重置密码
     */
//    @RequiresPermissions("webadmin:emloyee:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody BytesmartEmployee employee)
    {
        bytesmartEmployeeService.checkEmployeeAllowed(employee);
        bytesmartEmployeeService.checkEmployeeDataScope(employee.getEmployeeId());
        employee.setPassword(SecurityUtils.encryptPassword(employee.getPassword()));
        employee.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartEmployeeService.resetPwd(employee));
    }


    //    获取当前用户信息（通过用户名）
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

    //      通过用户id获取用户信息
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
//        Long employeeId = 1L;
//        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(employeeId);

        //之前正常使用的
        BytesmartEmployee bytesmartEmployee = bytesmartEmployeeService.selectEmployeeById(SecurityUtils.getUserId());


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

    /**
     * 状态修改
     */
//    @RequiresPermissions("webadmin:employee:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody BytesmartEmployee employee)
    {
        bytesmartEmployeeService.checkEmployeeAllowed(employee);
        bytesmartEmployeeService.checkEmployeeDataScope(employee.getEmployeeId());
        employee.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(bytesmartEmployeeService.updateEmployeeStatus(employee));
    }

    /**
     * 根据用户编号获取授权角色
     */
//    @RequiresPermissions("webadmin:employee:query")
    @GetMapping("/authRole/{employeeId}")
    public AjaxResult authRole(@PathVariable("employeeId") Long employeeId)
    {
        AjaxResult ajax = AjaxResult.success();
        BytesmartEmployee employee = bytesmartEmployeeService.selectEmployeeById(employeeId);
        List<BytesmartRole> roles = bytesmartRoleService.selectRolesByEmployeeId(employeeId);
        ajax.put("employeeId", employeeId);
        ajax.put("roles", BytesmartEmployee.isAdmin(employeeId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return ajax;
    }

    /**
     * 用户授权角色
     */
//    @RequiresPermissions("webadmin:employeeedit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PutMapping("/authRole")
    public AjaxResult insertAuthRole(Long employeeId, Long[] roleIds)
    {
        bytesmartEmployeeService.checkEmployeeDataScope(employeeId);
        bytesmartEmployeeService.insertEmployeeAuth(employeeId, roleIds);
        return success();
    }

    //获取部门树列表
    @GetMapping("/deptTree")
    public AjaxResult deptTree(BytesmartDept dept)
    {
        return success(bytesmartDeptService.selectDeptTreeList(dept));
    }


}
