package com.bytesmart.springsecurity.handler;

import com.alibaba.fastjson2.JSON;
import com.bytesmart.common.core.constant.CacheConstants;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.redis.service.RedisService;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.springsecurity.service.WebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义退出处理类 返回成功
 *
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private WebTokenService tokenService;

    @Autowired
    private RedisService redisService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        WebLoginUser webloginUser = tokenService.getWebLoginUser(request);
        if (StringUtils.isNotNull(webloginUser))
        {
//            String userName = webloginUser.getUsername();
            // 删除用户缓存记录
            redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + webloginUser.getUserkey());
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
