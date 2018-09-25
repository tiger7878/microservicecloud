package com.monkey.springcloud.service;

import com.monkey.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级工厂类，当出现服务提供者停止时会执行这里的方法，正常时不会
 * 使用工厂可以解耦
 * @author: monkey
 * @date: 2018/9/25 20:09
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            //本次演示只实现了一个，其他的也需要实现
            @Override
            public Dept get(long id) {
                return new Dept()
                        .setDeptno(id)
                        .setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
                        .setDb_source("no this database in MySQL");
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
