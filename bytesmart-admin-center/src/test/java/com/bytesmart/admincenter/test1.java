package com.bytesmart.admincenter;

import com.bytesmart.admincenter.mapper.BytesmartEmployeeMapper;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class test1 {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.matches("123456", "$2a$10$ewkx7HMtMmEtWEBVw08kXuzHNaKmMMnFQ5C9TlBtU4cT7aYK5Yw4C"));

//        String encode1 = passwordEncoder.encode("123456");
//        String encode2 = passwordEncoder.encode("123456");
//        System.out.println(encode1);
//        System.out.println(encode2);

    }
}

