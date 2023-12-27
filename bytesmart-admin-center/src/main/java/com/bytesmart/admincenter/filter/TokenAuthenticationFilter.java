package com.bytesmart.admincenter.filter;


import com.bytesmart.admincenter.Utils.TokenUtils;
import com.bytesmart.admincenter.domain.WebLoginUser;
import com.bytesmart.apisystem.model.LoginUser;
import com.bytesmart.common.core.utils.JwtUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.uuid.UUID;
import com.bytesmart.common.redis.service.RedisService;
import com.bytesmart.common.security.service.TokenService;
import com.bytesmart.common.security.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 前端传来个请求，解析token里面userkey，作为redis key查询其对象，对象就是一个WebLoginUser对象
        WebLoginUser webLoginUser = tokenUtils.getWebLoginUser(request);


        if (StringUtils.isNotNull(webLoginUser))
        {
            // 验证其有效期是否过期
            tokenUtils.verifyToken(webLoginUser);

            //实例化UsernamePasswordAuthenticationToken，把获取的信息等封装到存入authenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(webLoginUser, null, webLoginUser.getAuthorities());

            //传参request到authenticationToken，
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //将当前信息缓存到SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        //校验出入的请求和返回的数据，放行
        chain.doFilter(request, response);
    }

}
