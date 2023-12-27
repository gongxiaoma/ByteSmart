package com.bytesmart.admincenter;

import com.bytesmart.admincenter.mapper.BytersmartRoleMenuMapper;
import com.bytesmart.admincenter.mapper.BytesmartEmployeeMapper;
import com.bytesmart.admincenter.mapper.BytesmartMenuMapper;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.constant.TokenConstants;
import com.bytesmart.common.core.utils.JwtUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class test1 {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BytesmartMenuMapper bytesmartMenuMapper;

    @Test
    public void TestPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.matches("123456", "$2a$10$ewkx7HMtMmEtWEBVw08kXuzHNaKmMMnFQ5C9TlBtU4cT7aYK5Yw4C"));

//        String encode1 = passwordEncoder.encode("123456");
//        String encode2 = passwordEncoder.encode("123456");
//        System.out.println(encode1);
//        System.out.println(encode2);
    }

    @Test
    public void testSelectMenuPermsByEmployeeId (){
        List<String> list = bytesmartMenuMapper.selectMenuPermsByEmployeeId(3L);
        System.out.println(list);
    }

    @Test
    public void test2(){

        String secret = TokenConstants.SECRET;

        // token里面需要存储什么信息，声明一个Map，添加需要在token里面存储的信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_KEY, 111);
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, 1);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, "gongxiaoma");

        // 生成token，需要传入存储在token里面的数据（比如claimsMap）、token秘钥、签名算法
        String token = Jwts.builder().setClaims(claimsMap).signWith(SignatureAlgorithm.HS512, secret).compact();
        System.out.println(token);

        // 解析token，需要传入token秘钥、上面生成的token，会获取到一个Claims对象
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        // 在Claims对象中get对应的属性值，并强转String
        String user_key = claims.get(SecurityConstants.USER_KEY).toString();
        String username = claims.get(SecurityConstants.DETAILS_USERNAME).toString();
        System.out.println(user_key);
        System.out.println(username);

    }




}

