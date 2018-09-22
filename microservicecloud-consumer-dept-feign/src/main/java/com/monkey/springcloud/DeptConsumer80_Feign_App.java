package com.monkey.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务启动类
 * @author: monkey
 * @date: 2018/9/22 12:33
 */
@SpringBootApplication
@EnableEurekaClient //Eureka客户端的支持
@EnableFeignClients(basePackages = {"com.monkey.springcloud"}) //添加Fegin的支持
@ComponentScan("com.monkey.springcloud")
public class DeptConsumer80_Feign_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class,args);
    }

}
