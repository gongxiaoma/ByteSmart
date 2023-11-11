package com.bytesmart.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.bytesmart.common.security.annotation.EnableRyFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 认证授权中心
 *
 * @author hd
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = {"com.bytesmart.*"})
public class BytesmartAuthCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartAuthCenterApplication.class, args);
        System.out.println("byteSmart认证授权中心启动成功");
    }

}
