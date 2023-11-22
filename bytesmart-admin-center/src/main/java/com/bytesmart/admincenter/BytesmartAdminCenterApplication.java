package com.bytesmart.admincenter;

import com.bytesmart.admincenter.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bytesmart.common.security.annotation.EnableRyFeignClients;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;


/**
 * 系统模块
 *
 * @author hd
 */

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.*"})
public class BytesmartAdminCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartAdminCenterApplication.class, args);
        System.out.println("byteSmart管理模块启动成功" );
    }
}

