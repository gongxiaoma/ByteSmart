package com.bytesmart.springsecurity.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

/**
 * 系统配置
 *
 * @author hd
 */
public class WebApplicationConfig
{
    /**
     * 时区配置
     */
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
//    {
//        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
//    }
}
