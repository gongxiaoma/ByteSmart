package com.bytesmart.webnetdisk;

//import com.bytesmart.springsecurity.annotation.EnableRyFeignClients;
import com.bytesmart.common.swagger.annotation.EnableCustomSwagger2;
import com.bytesmart.springsecurity.annotation.EnableCustomConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableCustomConfig
@EnableCustomSwagger2
//@EnableRyFeignClients
@SpringBootApplication(scanBasePackages = {"com.bytesmart.web*"})
public class BytesmartWebNetdiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BytesmartWebNetdiskApplication.class, args);
        System.out.println("byteSmart web网盘模块启动成功" );
    }

}
