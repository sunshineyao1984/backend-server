package com.frog.backend.server.system.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Description 启动类
 *
 * @author yxy
 * @date 2021-01-26
 */
@MapperScan(basePackages = {"com.frog.backend.server.system.service.mapper"})
@ComponentScan(basePackages = {"com.frog.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class,args);
    }

}
