package com.monkey.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced //Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端的负载均衡的工具，此注解表示进行负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /*@Bean
    public IRule iRule(){
        //指定Ribbon的算法，替代默认的轮询算法
//        return new RandomRule();//随机算法
        return new RetryRule();//重试
    }*/

}
