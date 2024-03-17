package com.bytesmart.apiwebadmin;

import com.bytesmart.apiwebadmin.domain.BytesmartEmployee;
import com.bytesmart.apiwebadmin.factory.RemoteUserFallbackFactory;
import com.bytesmart.apiwebadmin.model.LoginEmployee;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.constant.ServiceNameConstants;
import com.bytesmart.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 * 
 * @author hd
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)

public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/employee/info/{username}")
    public R<LoginEmployee> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param bytesmartEmployee 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/employee/register")
    public R<Boolean> registerUserInfo(@RequestBody BytesmartEmployee bytesmartEmployee, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
