package com.bytesmart.webtask.service.impl;

import com.bytesmart.apisystem.RemoteEmployee;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.enums.UserStatus;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


@Component
public class BytesmartGetEmployeeService {

    @Autowired
    RemoteEmployee remoteEmployee;


    public LoginEmployee getEmployee(@PathVariable Long employeeId)
    {
        //远程调用根据ID获取用户信息的接口（该接口在api-system中，有定义feign的接口，该接口会调用对应路径的webadmin控制器）
        R<LoginEmployee> employeeResult = remoteEmployee.getEmployeeInfoById(employeeId, SecurityConstants.INNER);

        if (StringUtils.isNull(employeeResult) || StringUtils.isNull(employeeResult.getData()))
        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + employeeId + " 不存在");
        }

        if (R.FAIL == employeeResult.getCode())
        {
            throw new ServiceException(employeeResult.getMsg());
        }

        LoginEmployee employeeInfo = employeeResult.getData();
        BytesmartEmployee employee = employeeResult.getData().getEmployee();

        if (UserStatus.DELETED.getCode().equals(employee.getDelFlag()))
        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + employeeId + " 已被删除");
        }

        if (UserStatus.DISABLE.getCode().equals(employee.getEmployeeStatus()))
        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + employeeId + " 已停用");
        }

        return employeeInfo;

    }

}
