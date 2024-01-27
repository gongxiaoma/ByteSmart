package com.bytesmart.springsecurity.handler;

import com.alibaba.fastjson2.JSON;
import com.bytesmart.common.core.constant.HttpStatus;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthenticationEntrypointImpl implements AuthenticationEntryPoint {

    //认证失败
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//      ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED,"用户认证失败请重新登录");
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));

    }
}


