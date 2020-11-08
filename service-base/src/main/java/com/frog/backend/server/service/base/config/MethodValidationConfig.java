package com.frog.backend.server.service.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Description GET请求参数校验处理器配置类
 *
 * @author yxy
 * @date 2020-10-13
 */
@Configuration
public class MethodValidationConfig {

    /**
     * 初始化GET请求参数校验处理器
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
