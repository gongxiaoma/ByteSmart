package com.bytesmart.webnetdisk.controller;

import com.bytesmart.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testA {

    @GetMapping("gxm")
    public AjaxResult test(){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", "A");
        ajax.put("roles", "B");
        ajax.put("permissions", "C");
        System.out.println("123456");
        return ajax;
    }
}
