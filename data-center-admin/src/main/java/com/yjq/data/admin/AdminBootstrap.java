package com.yjq.data.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot 启动类
 * @date 2017/6/7
 * @author netyjq@gmail.com
 */
@SpringBootApplication
@MapperScan("com.yjq.data.admin.mapper")
@ComponentScan("com.yjq")
@ImportResource("classpath:spring-dubbo.xml")
@EnableScheduling
@EnableAspectJAutoProxy
public class AdminBootstrap extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AdminBootstrap.class);
    }
}

