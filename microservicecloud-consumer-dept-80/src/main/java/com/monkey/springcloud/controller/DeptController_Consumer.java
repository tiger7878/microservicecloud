package com.monkey.springcloud.controller;

import com.monkey.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费者控制器
 * @author: monkey
 * @date: 2018/9/17 21:34
 */
@RestController
@RequestMapping("/consumer/dept")
public class DeptController_Consumer {

//    public static final String REST_URL_PREFIX = "http://localhost:8001";//没有使用ribbon时的做法
    public static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";//使用ribbon的做法，用某个微服务的名字

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    //对外暴露查询所有的微服务
    @RequestMapping(value = "/discovery",method = RequestMethod.GET)
    public Object discovery(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery",Object.class);
    }

}
