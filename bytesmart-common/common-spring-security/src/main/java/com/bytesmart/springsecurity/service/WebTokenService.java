package com.bytesmart.springsecurity.service;

import com.bytesmart.common.core.constant.CacheConstants;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.utils.JwtUtils;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.ip.IpUtils;
import com.bytesmart.common.core.utils.uuid.IdUtils;
import com.bytesmart.common.redis.service.RedisService;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * token验证处理
 *
 * @author hd
 */

@Component
public class WebTokenService
{
    private static final Logger log = LoggerFactory.getLogger(WebTokenService.class);

    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;


    /**
     * WEB创建令牌
     */
    public String createWebToken(WebLoginUser webloginUser)
    {
        String userKey = IdUtils.fastUUID();
        Long userId = webloginUser.getEmployee().getEmployeeId();
        String userName = webloginUser.getEmployee().getUserName();
        webloginUser.setUserkey(userKey);
        webloginUser.setUserid(userId);
        webloginUser.setUsername(userName);
        webloginUser.setIpaddr(IpUtils.getIpAddr());
        refreshWebToken(webloginUser);

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, userKey);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        return JwtUtils.createToken(claimsMap);
    }



    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public WebLoginUser getWebLoginUser()
    {
        return getWebLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public WebLoginUser getWebLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = WebSecurityUtils.getToken(request);
        return getWebLoginUser(token);
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public WebLoginUser getWebLoginUser(String token)
    {
        WebLoginUser webuser = null;
        try
        {
            if (StringUtils.isNotEmpty(token))
            {
                String userkey = JwtUtils.getUserKey(token);
                webuser = redisService.getCacheObject(getTokenKey(userkey));
                return webuser;
            }
        }
        catch (Exception e)
        {
            log.error("获取用户信息异常'{}'", e.getMessage());
        }
        return webuser;
    }

    /**
     * 删除用户缓存信息
     */
    public void delLoginUser(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userkey = JwtUtils.getUserKey(token);
            redisService.deleteObject(getTokenKey(userkey));
        }
    }


    /**
     * 验证令牌有效期，相差不足120分钟，自动刷新缓存
     *
     * @param loginWebUser
     */
    public void verifyWebToken(WebLoginUser webloginUser)
    {
        long expireTime = webloginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshWebToken(webloginUser);
        }
    }


    /**
     * 刷新令牌有效期
     *
     * @param webloginUser 登录信息
     */
    public void refreshWebToken(WebLoginUser webLoginUser)
    {
        webLoginUser.setLoginTime(System.currentTimeMillis());
        webLoginUser.setExpireTime(webLoginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(webLoginUser.getUserkey());
        redisService.setCacheObject(userKey, webLoginUser, expireTime, TimeUnit.MINUTES);
    }

    // 入参由token改查了uuid
    private String getTokenKey(String uuid)
    {
        return ACCESS_TOKEN + uuid;
    }
}
