package com.bytesmart.apisystem.model;

import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

public interface RemotePost {

    /**
     * 通过用户ID查询岗位信息
     *
     * @param postid ID
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping("/{postId}")
    public R<LoginEmployee> getEmployeeInfoById(@PathVariable("postId") Long employeeId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
