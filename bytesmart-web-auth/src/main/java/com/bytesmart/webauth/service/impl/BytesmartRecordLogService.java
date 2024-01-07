package com.bytesmart.webauth.service.impl;

import com.bytesmart.webauth.service.IBytesmartRecordLogService;
import com.bytesmart.apisystem.RemoteLogService;
import com.bytesmart.apisystem.domain.SysLogininfor;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记录日志方法
 *
 * @author hd
 */
@Service
public class BytesmartRecordLogService implements IBytesmartRecordLogService {

    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息内容
     * @return
     */
    @Override
    public void recordLogininfor(String username, String status, String message)
    {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr());
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
