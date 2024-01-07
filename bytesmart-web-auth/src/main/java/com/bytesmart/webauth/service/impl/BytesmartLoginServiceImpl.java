package com.bytesmart.webauth.service.impl;

import com.bytesmart.webauth.Utils.TokenUtils;
import com.bytesmart.webauth.context.AuthenticationContextHolder;
import com.bytesmart.webauth.domain.WebLoginUser;
import com.bytesmart.webadmin.service.IBytesmartEmployeeService;
import com.bytesmart.webauth.service.IBytesmartLoginService;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.exception.user.UserPasswordNotMatchException;
import com.bytesmart.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class BytesmartLoginServiceImpl implements IBytesmartLoginService
{

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

//    @Autowired
//    private RedisService redisCache;

    @Autowired
    private IBytesmartEmployeeService employeeService;
//
//    @Autowired
//    private ISysConfigService configService;

    @Autowired
    private BytesmartRecordLogService recordLogService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
//        // 验证码校验
//        validateCaptcha(username, code, uuid);
//        // 登录前置校验
//        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication = null;

        try
        {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
//                recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
                throw new UserPasswordNotMatchException();
            }
            else
            {
//                recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "登录失败");
                throw new ServiceException(e.getMessage());
            }
        }
        finally
        {
            AuthenticationContextHolder.clearContext();
        }
//        recordLogService.recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        WebLoginUser loginUser = (WebLoginUser) authentication.getPrincipal();
//        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenUtils.createToken(loginUser);
    }


//    @Override
//    public int loginout(){
//        //获取SecurityContextHolder的key值
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        WebLoginUser webLoginUser =  (WebLoginUser) authentication.getPrincipal();
//        String userkey = "login_uuids-" + webLoginUser.getUserkey();
//        //删除redis里的值
//        redisService.deleteObject(userkey);
//        return 1;
//    }





    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
//    public void validateCaptcha(String username, String code, String uuid)
//    {
//        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha))
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
//    }

//    /**
//     * 登录前置校验
//     * @param username 用户名
//     * @param password 用户密码
//     */
//    public void loginPreCheck(String username, String password)
//    {
//        // 用户名或密码为空 错误
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
//            throw new UserNotExistsException();
//        }
//        // 密码如果不在指定范围内 错误
//        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
//                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//            throw new UserPasswordNotMatchException();
//        }
//        // 用户名不在指定范围内 错误
//        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
//                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//            throw new UserPasswordNotMatchException();
//        }
//        // IP黑名单校验
//        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
//        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
//        {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
//            throw new BlackListException();
//        }
//    }

//    /**
//     * 记录登录信息
//     *
//     * @param userId 用户ID
//     */
//    public void recordLoginInfo(Long userId)
//    {
//        SysUser sysUser = new SysUser();
//        sysUser.setUserId(userId);
//        sysUser.setLoginIp(IpUtils.getIpAddr());
//        sysUser.setLoginDate(DateUtils.getNowDate());
//        employeeService.updateUserProfile(sysUser);
//    }
}
