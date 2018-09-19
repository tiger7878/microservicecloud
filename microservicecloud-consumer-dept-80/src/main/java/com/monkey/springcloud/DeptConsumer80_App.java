package com.monkey.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动服务的类
 * @author: monkey
 * @date: 2018/9/17 21:42
 */
@SpringBootApplication
@EnableEurekaClient //标示为eureka的客户端，因为ribbon需要和eureka整合
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class,args);
    }

}
