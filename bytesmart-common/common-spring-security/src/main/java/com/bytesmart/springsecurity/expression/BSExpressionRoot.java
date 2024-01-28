package com.bytesmart.springsecurity.expression;

import com.bytesmart.springsecurity.domain.WebLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("bs")
public class BSExpressionRoot
{

    public boolean hasAuthority(String authority)
    {
     //获取当前用户的权限
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     WebLoginUser webLoginUser = (WebLoginUser) authentication.getPrincipal();
     Set<String> permissions = webLoginUser.getPermissions();

     //判断用户权限集合是否存在
     return permissions.contains(authority);
     }
}
