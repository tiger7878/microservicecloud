package com.monkey.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载规则
 * @author: monkey
 * @date: 2018/9/22 10:31
 */
@Configuration
public class MySlefRule {

    @Bean
    public IRule myRule(){
        return new MyRandomRule();//自定一定的负载规则
    }

}
