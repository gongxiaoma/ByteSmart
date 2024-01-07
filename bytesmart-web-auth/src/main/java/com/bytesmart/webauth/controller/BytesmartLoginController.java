package com.bytesmart.webauth.controller;

import com.bytesmart.webauth.service.IBytesmartLoginService;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.domain.LoginBody;
import com.bytesmart.common.core.web.domain.AjaxResult;
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
    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = bytesmartLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        ajax.put("我是key", "我是值");
        return ajax;
    }

    //登出
//    @RequestMapping("logout")
//    public AjaxResult loginout()
//    {
//        System.out.println("111111");
//        AjaxResult ajax = AjaxResult.success();
//        int loginout = bytesmartLoginService.loginout();
//        System.out.println(loginout);
//        ajax.put("返回值", loginout);
//        return ajax;
//    }


    /**
     * 测试方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */

    @GetMapping("test")
    @PreAuthorize("hasAuthority('system:employee:list')")
//    @PreAuthorize("hasAuthority('test')")
    public AjaxResult test()
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("test", "9999");
        return ajax;
    }

}
