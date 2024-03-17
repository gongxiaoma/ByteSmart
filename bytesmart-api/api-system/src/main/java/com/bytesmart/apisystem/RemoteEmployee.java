package com.bytesmart.apisystem;

import com.bytesmart.apisystem.model.LoginEmployee;
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
@FeignClient(contextId = "remoteEmployeeService", value = ServiceNameConstants.WEBADMIN_SERVICE, path = "/userinfo")
public interface RemoteEmployee
{


    /**
     * 通过用户ID查询用户信息
     *
     * @param userid ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/{employeeId}")
    public R<LoginEmployee> getEmployeeInfoById(@PathVariable("employeeId") Long employeeId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
