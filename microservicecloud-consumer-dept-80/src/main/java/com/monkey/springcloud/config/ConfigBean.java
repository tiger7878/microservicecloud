package com.monkey.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 配置类
 * @author: monkey
 * @date: 2018/9/17 21:33
 */
@Configuration
public class ConfigBean {

    //创建一个RestTemplate去调用rest服务
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
