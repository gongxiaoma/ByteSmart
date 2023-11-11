package com.bytesmart.gatewaycenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 *
 * @author hd
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BytesmartGatewayCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartGatewayCenterApplication.class, args);
        System.out.println("byteSmart网关启动成功");
    }
}

