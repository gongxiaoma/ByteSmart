package com.bytesmart.systemcenter;

import com.bytesmart.systemcenter.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bytesmart.common.security.annotation.EnableRyFeignClients;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统模块
 *
 * @author hd
 */

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.*"})
public class BytesmartSystemCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartSystemCenterApplication.class, args);
        System.out.println("byteSmart系统模块启动成功" );
    }
}
