package com.bytesmart.admincenter.controller;

import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.admincenter.service.IBytesmartDeptService;
import com.bytesmart.admincenter.service.IBytesmartEmployeeService;
import com.bytesmart.admincenter.service.IBytesmartPostService;
import com.bytesmart.admincenter.service.IBytesmartRoleService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.apisystem.domain.SysRole;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/list")
    public TableDataInfo list(BytesmartEmployee employee)
    {
        startPage();
        List<BytesmartEmployee> list = bytesmartEmployeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    @GetMapping(value = { "/", "/{employeeId}" })
    public AjaxResult getInfo(@PathVariable(value = "employeeId", required = false) Integer employeeId)
    {
            return success(bytesmartEmployeeService.selectEmployeeById(employeeId));
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
    public AjaxResult remove(@PathVariable Integer[] employeeIds)
    {
        return toAjax(bytesmartEmployeeService.deleteEmployeeByIds(employeeIds));
    }

}
