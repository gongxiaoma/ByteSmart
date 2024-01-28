package com.bytesmart.springsecurity.auth;

import com.bytesmart.common.core.exception.auth.NotLoginException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.springsecurity.service.WebTokenService;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;


/**
 * Token 权限验证，逻辑实现类
 * 
 * @author hd
 */
public class AuthLogic
{
    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    public WebTokenService webTokenService = SpringUtils.getBean(WebTokenService.class);

    /**
     * 会话注销
     */
    public void logout()
    {
        String token = WebSecurityUtils.getToken();
        if (token == null)
        {
            return;
        }
        logoutByToken(token);
    }

    /**
     * 会话注销，根据指定Token
     */
    public void logoutByToken(String token)
    {
        webTokenService.delLoginUser(token);
    }

    /**
     * 检验用户是否已经登录，如未登录，则抛出异常
     */
    public void checkLogin()
    {
        getLoginUser();
    }

    /**
     * 获取当前用户缓存信息, 如果未登录，则抛出异常
     * 
     * @return 用户缓存信息
     */
    public WebLoginUser getLoginUser()
    {
        String token = WebSecurityUtils.getToken();
        if (token == null)
        {
            throw new NotLoginException("未提供token");
        }
        WebLoginUser webLoginUser = WebSecurityUtils.getWebLoginUser();
        if (webLoginUser == null)
        {
            throw new NotLoginException("无效的token");
        }
        return webLoginUser;
    }

    /**
     * 获取当前用户缓存信息, 如果未登录，则抛出异常
     * 
     * @param token 前端传递的认证信息
     * @return 用户缓存信息
     */
    public WebLoginUser getWebLoginUser(String token)
    {
        return webTokenService.getWebLoginUser(token);
    }

    /**
     * 验证当前用户有效期, 如果相差不足120分钟，自动刷新缓存
     * 
     * @param loginUser 当前用户信息
     */
    public void verifyLoginUserExpire(WebLoginUser webLoginUser)
    {
        webTokenService.verifyWebToken(webLoginUser);
    }

}
