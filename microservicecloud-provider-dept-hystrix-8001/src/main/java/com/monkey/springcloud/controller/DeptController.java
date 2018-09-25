package com.monkey.springcloud.controller;

import com.monkey.springcloud.entities.Dept;
import com.monkey.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: monkey
 * @date: 2018/9/17 11:30
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    //这种方式不好，业务代码和服务熔断耦合到一起了，使用DeptClientServiceFallbackFactory工厂来分开
    @HystrixCommand(fallbackMethod ="processHystrix_Get")
    public Dept get(@PathVariable("id") Long id){

        Dept dept=deptService.get(id);
        if (null==dept){
            throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
        }

        return dept;
    }

    //熔断后调用的方法
    public Dept processHystrix_Get(@PathVariable("id")Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in MySQL");
    }

}

