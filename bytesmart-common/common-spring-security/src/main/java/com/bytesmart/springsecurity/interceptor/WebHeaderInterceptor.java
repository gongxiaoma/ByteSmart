package com.bytesmart.springsecurity.interceptor;

import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.context.WebSecurityContextHolder;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.springsecurity.auth.WebAuthUtil;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 *
 * @author hd
 */
public class WebHeaderInterceptor implements AsyncHandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (!(handler instanceof HandlerMethod))
        {
            return true;
        }

        WebSecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        WebSecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        WebSecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));

        System.out.println(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        System.out.println(ServletUtils.getHeader(request, SecurityConstants.DETAILS_USERNAME));
        System.out.println(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));

        String token = WebSecurityUtils.getToken();
        if (StringUtils.isNotEmpty(token))
        {
            WebLoginUser webLoginUser = WebAuthUtil.getWebLoginUser(token);
            if (StringUtils.isNotNull(webLoginUser))
            {
                WebAuthUtil.verifyWebLoginUserExpire(webLoginUser);
                WebSecurityContextHolder.set(SecurityConstants.LOGIN_USER, webLoginUser);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        WebSecurityContextHolder.remove();
    }
}
