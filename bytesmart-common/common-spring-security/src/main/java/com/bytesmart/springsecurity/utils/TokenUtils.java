package com.bytesmart.springsecurity.utils;

import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.common.core.constant.CacheConstants;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.constant.TokenConstants;
import com.bytesmart.common.core.utils.ServletUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.ip.IpUtils;
import com.bytesmart.common.core.utils.uuid.IdUtils;
import com.bytesmart.common.redis.service.RedisService;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    @Autowired
    private RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private final static long expireTime = CacheConstants.EXPIRATION;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    private final static Long MILLIS_MINUTE_TEN = CacheConstants.REFRESH_TIME * MILLIS_MINUTE;

    // 生成和解析token的盐
    public static String websecret = TokenConstants.WEBSECRET;


    /**
     * 解析token，获取用户身份信息
     *
     * @return 用户信息
     */
    public WebLoginUser getWebLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);

        //打印出携带的令牌
//        System.out.println(token);
        if (StringUtils.isNotEmpty(token))
        {
            //如果令牌非空，进行下一步
            try
            {
                // 解析令牌
                Claims claims = parseToken(token);

                //获取令牌中的里的信息（WEB_LOGIN_USER_KEY值）
                String webUserKey = (String) claims.get(Constants.WEB_LOGIN_USER_KEY);

                //传入webUserKey值，获取redis中的userkey值
                String redisUserKey = getRedisKey(webUserKey);

                //调用方法getCacheObject，传入参数redisUserKey值
                WebLoginUser user = redisService.getCacheObject(redisUserKey);

//                System.out.println(user);

                //返回值类型WebLoginUser的对象user
                return user;
            }
            catch (Exception e)
            {
                log.error("获取web用户信息异常'{}'", e.getMessage());
            }
        }
        //如果令牌为空，返回null
        return null;
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, websecret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static Claims parseToken(String token)
    {
        //解析令牌，
        return Jwts.parser().setSigningKey(websecret).parseClaimsJws(token).getBody();
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(WebLoginUser webLoginUser)
    {
        long expireTime = webLoginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            storageLoginUser(webLoginUser);
        }
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 创建令牌
     *
     * @param WebLoginUser用户信息
     * @return 令牌
     */
    public String createToken(WebLoginUser webloginUser)
    {
        // 生成一个UUID，复制给变量userKey
        String userKey = IdUtils.fastUUID();

        // 设置userKey
        webloginUser.setUserkey(userKey);

        setUserAgent(webloginUser);

        // 将webloginUser已认证的对象，存入Redis
        storageLoginUser(webloginUser);

        // JWT创建token，指定token载荷部分存储哪些信息
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.WEB_LOGIN_USER_KEY, userKey);
        return createToken(claims);
    }


    /**
     * 刷新令牌有效期
     *
     * @param webloginUser 登录信息
     */
    public void storageLoginUser(WebLoginUser webloginUser)
    {
        // 获取当前时间并调用WebLoginUser实体类中setLoginTime方法存入登录时间
        webloginUser.setLoginTime(System.currentTimeMillis());

        // 通过调用webloginUser实体类中getLoginTime方法获取LoginTime登录时间（就是上一步设置的时间），然后加上常量定义的过期时间（比如是720分钟），最终形成过期到期时间（存redis需要）
        webloginUser.setExpireTime(webloginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

        // 根据uuid将loginUser缓存
        String userKey = getRedisKey(webloginUser.getUserkey());
        redisService.setCacheObject(userKey, webloginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getRedisKey(String userKey)
    {
        return "login_uuids-" + userKey;
    }

    /**
     * 设置用户代理信息
     *
     * @param webloginUser登录信息
     */
    public void setUserAgent(WebLoginUser webloginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        webloginUser.setIpaddr(ip);
//        webloginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        webloginUser.setBrowser(userAgent.getBrowser().getName());
        webloginUser.setOs(userAgent.getOperatingSystem().getName());
    }
}
