package com.bytesmart.webadmin.controller;

import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.common.core.constant.CacheConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.redis.service.RedisService;
import com.bytesmart.webadmin.domain.BytesmartEmployeeOnline;
import com.bytesmart.webadmin.service.IBytesmartEmployeeOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户监控
 *
 * @author hd
 */
@RestController
//@RequestMapping("/online")
public class BytesmartEmployeeOnlineController extends BaseController {
    @Autowired
    private IBytesmartEmployeeOnlineService employeeOnlineService;

    @Autowired
    private RedisService redisService;

    //    @RequiresPermissions("monitor:online:list")
    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName)
    {
        Collection<String> keys = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
        List<BytesmartEmployeeOnline> emplyeeOnlineList = new ArrayList<BytesmartEmployeeOnline>();
        for (String key : keys)
        {
            LoginEmployee employee = redisService.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                emplyeeOnlineList.add(employeeOnlineService.selectOnlineByInfo(ipaddr, userName, employee));
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                emplyeeOnlineList.add(employeeOnlineService.selectOnlineByIpaddr(ipaddr, employee));
            }
            else if (StringUtils.isNotEmpty(userName))
            {
                emplyeeOnlineList.add(employeeOnlineService.selectOnlineByUserName(userName, employee));
            }
            else
            {
                emplyeeOnlineList.add(employeeOnlineService.loginUserToUserOnline(employee));
            }
        }
        Collections.reverse(emplyeeOnlineList);
        emplyeeOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(emplyeeOnlineList);
    }

    /**
     * 强退用户
     */
//    @RequiresPermissions("monitor:online:forceLogout")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId)
    {
        redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + tokenId);
        return success();
    }

}
