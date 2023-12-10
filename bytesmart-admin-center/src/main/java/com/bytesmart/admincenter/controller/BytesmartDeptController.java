package com.bytesmart.admincenter.controller;


import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.admincenter.service.IBytesmartDeptService;
import com.bytesmart.apisystem.domain.BytesmartDept;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class BytesmartDeptController extends BaseController {

    @Autowired
    private IBytesmartDeptService bytesmartDeptService;

    @GetMapping("/list")
    public AjaxResult list(BytesmartDept dept)
    {
        List<BytesmartDept> list = bytesmartDeptService.selectDeptList(dept);
        return success(list);
    }

    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId)
    {
        return success(bytesmartDeptService.selectDeptById(deptId));
    }

    @PutMapping
    public AjaxResult edit(@Validated @RequestBody BytesmartDept dept){
        return toAjax(bytesmartDeptService.updateDept(dept));
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody BytesmartDept dept){
        return toAjax(bytesmartDeptService.insertDept(dept));
    }

    @DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds){
        return toAjax(bytesmartDeptService.deleteDeptByIds(deptIds));
    }


}
