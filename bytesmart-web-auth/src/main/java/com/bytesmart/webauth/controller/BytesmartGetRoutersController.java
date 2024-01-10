package com.bytesmart.webauth.controller;

import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.domain.BytesmartMenu;
import com.bytesmart.webadmin.service.IBytesmartMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BytesmartGetRoutersController extends BaseController {

    @Autowired
    private IBytesmartMenuService bytesmartMenuService;


    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long employeeId = 1L;
//        Long employeeId = WebSecurityUtils.getEmployeeId();
        List<BytesmartMenu> menus = bytesmartMenuService.selectMenuTreeByEmployeeId(employeeId);
        return success(bytesmartMenuService.buildMenus(menus));
    }
}
