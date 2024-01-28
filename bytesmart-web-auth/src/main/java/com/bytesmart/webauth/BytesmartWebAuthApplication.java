package com.bytesmart.webauth;

import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;
import com.bytesmart.springsecurity.annotation.EnableRyFeignClients;
import com.bytesmart.springsecurity.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.*"})
public class BytesmartWebAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytesmartWebAuthApplication.class, args);
        System.out.println("byteSmart web认证模块启动成功" );
    }

}
