package com.bytesmart.webauth.controller;

import com.bytesmart.webauth.service.IBytesmartLoginService;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.domain.LoginBody;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.springsecurity.expression.BSExpressionRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class BytesmartLoginController {

    @Autowired
    private IBytesmartLoginService bytesmartLoginService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = bytesmartLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    @GetMapping("test")
    @PreAuthorize("@bs.hasAuthority('web:mail:send')")
    public AjaxResult test(){
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", "A");
        ajax.put("roles", "B");
        ajax.put("permissions", "C");
        return ajax;
    }

}
