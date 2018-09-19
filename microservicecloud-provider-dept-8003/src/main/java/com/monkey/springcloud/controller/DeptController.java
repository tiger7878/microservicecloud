package com.monkey.springcloud.controller;

import com.monkey.springcloud.entities.Dept;
import com.monkey.springcloud.service.DeptService;
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

    //服务发现
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id){
        return deptService.get(id);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return deptService.list();
    }

    /**
     * 服务发现，展示出所有的微服务
     * @return
     */
    @RequestMapping(value = "/discovery",method = RequestMethod.GET)
    public Object discovery(){
        //获取所有的微服务
        List<String> list=discoveryClient.getServices();
        System.out.println("**********" + list);

        //获取我们指定的某个微服务（比如：MICROSERVICECLOUD-DEPT）
        List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.discoveryClient;
    }

}

