package com.monkey.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * 自定义负载规则，轮询策略，每次执行5次后，再轮询
 *
 * @author: monkey
 * @date: 2018/9/22 10:48
 */
public class MyRandomRule extends AbstractLoadBalancerRule {

    /**
     * 每次轮询执行次数，到达5次归零
     */
    private int totalCount = 0;

    /**
     * 当前索引，达到3次后归零
     */
    private int currentIndex = 0;

    public MyRandomRule() {
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while (server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//所有可以到达的服务
                List<Server> allList = lb.getAllServers();//所有服务
                int serverCount = allList.size();//所有服务个数
                if (serverCount == 0) {
                    return null;
                }

                //每个服务调用5次以后再轮询
                if (totalCount < 5) {
//                    server = (Server) upList.get(currentIndex);//做法1
                    totalCount ++;
                } else {
//                    totalCount=0;//做法1
                    totalCount=1;//做法2
                    currentIndex++;
                    if (currentIndex >= upList.size()) {
                        currentIndex = 0;
                    }
                }
                server = (Server) upList.get(currentIndex);//做法2
                System.out.println(currentIndex+"："+totalCount);

                if (server == null) {
                    Thread.yield();
                } else {

                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
