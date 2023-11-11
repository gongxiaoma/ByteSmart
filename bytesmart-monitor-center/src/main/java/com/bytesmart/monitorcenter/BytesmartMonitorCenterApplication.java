package com.bytesmart.monitorcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 *
 * @author hd
 */
@EnableAdminServer
@SpringBootApplication
public class BytesmartMonitorCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartMonitorCenterApplication.class, args);
        System.out.println("byteSmart监控中心启动成功");
    }
}
