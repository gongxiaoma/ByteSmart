package com.bytesmart.filecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 *
 * @author hd
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BytesmartFileCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartFileCenterApplication.class, args);
        System.out.println("byteSmart文件服务模块启动成功");
    }
}
