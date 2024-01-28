package com.bytesmart.springsecurity.handler;

import com.alibaba.fastjson2.JSON;
import com.bytesmart.common.core.constant.HttpStatus;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //权限失败


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        int code = HttpStatus.FORBIDDEN;
        String msg = StringUtils.format("请求访问：{}，权限不足，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
