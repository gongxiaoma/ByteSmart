package com.bytesmart.webadmin;

import com.bytesmart.common.security.annotation.EnableRyFeignClients;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;
import com.bytesmart.webadmin.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.*"})
public class BytesmartWebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytesmartWebAdminApplication.class, args);
        System.out.println("byteSmart管理模块启动成功" );
    }

}
