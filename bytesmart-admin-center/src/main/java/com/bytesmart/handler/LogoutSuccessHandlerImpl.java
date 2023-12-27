package com.bytesmart.handler;

import com.bytesmart.admincenter.Utils.TokenUtils;
import com.bytesmart.admincenter.domain.WebLoginUser;
import com.bytesmart.apisystem.model.LoginUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson2.JSON;
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
    private TokenUtils tokenUtils;

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
        WebLoginUser webloginUser = tokenUtils.getWebLoginUser(request);
        if (StringUtils.isNotNull(webloginUser))
        {
//            String userName = webloginUser.getUsername();
            // 删除用户缓存记录
            redisService.deleteObject("login_uuids-" + webloginUser.getUserkey());
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("退出成功")));
    }
}
