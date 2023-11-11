package com.bytesmart.jobcenter;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bytesmart.common.security.annotation.EnableCustomConfig;
import com.bytesmart.common.security.annotation.EnableRyFeignClients;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 *
 * @author hd
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.bytesmart.jobcenter.mapper")
public class BytesmartJobCenterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BytesmartJobCenterApplication.class, args);
        System.out.println("byteSmart定时任务模块启动成功");
    }
}
