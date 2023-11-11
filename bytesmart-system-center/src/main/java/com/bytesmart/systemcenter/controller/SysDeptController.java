package com.bytesmart.systemcenter.controller;

import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.apisystem.domain.SysDept;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 * 
 * @author hd
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController
{

    /**
     * 获取部门列表
     */
    @GetMapping("/list")
    public String list(SysDept dept)
    {
        System.out.println("ddddd");
        return "dddddd";
    }

}
