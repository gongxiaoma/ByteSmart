package com.bytesmart.webtask;

import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;
import com.bytesmart.springsecurity.annotation.EnableCustomConfig;
import com.bytesmart.springsecurity.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.*"})
public class BytesmartWebTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(BytesmartWebTaskApplication.class, args);
        System.out.println("byteSmart 任务认证模块启动成功");
    }
}
