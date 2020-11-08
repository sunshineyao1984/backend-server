package com.frog.backend.server.member.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * Description 启动类
 *
 * @author yxy
 * @date 2020-10-09
 */
@MapperScan(basePackages = {"com.frog.backend.server.member.service.mapper"})
@ComponentScan(basePackages = {"com.frog.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class MemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberServiceApplication.class,args);
    }
}
