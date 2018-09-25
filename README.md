# 2018-9-18 23:23:51 添加Eureka集群配置
## 修改host文件：C:\Windows\System32\drivers\etc添加以下内容 
### SpringCloud的Eureka集群配置 
127.0.0.1	eureka7001.com<br />
127.0.0.1	eureka7002.com<br />
127.0.0.1	eureka7003.com<br />
## 测试： 
http://eureka7001.com:7001/<br />
http://eureka7002.com:7002/<br />
http://eureka7003.com:7003/<br />

# 2018-9-19 21:47:19 Ribbon初步配置<br />
## 测试：
http://localhost/consumer/dept/get/3 <br />

# 2018-9-19 22:51:15 一个微服务，多个实例，即多个provider
## ①创建两个数据库：
cloudDB02、cloudDB03（参考cloudDB01的脚本） 
## ②创建两个项目：
microservicecloud-provider-dept-8002 <br />
microservicecloud-provider-dept-8003
## ②修改两个项目：
application.yml <br />
注意微服务名称不能改变 <br />
instance-id 需要修改
## 测试：
http://eureka7001.com:7001/ #查看一个微服务有几个实例 <br />
http://localhost/consumer/dept/list #看数据

# 2018-9-22 12:49:27 Feign负载均衡
## 测试：
http://localhost/consumer/dept/list

# 2018-9-25 06:50:31 Hystrix-服务熔断
## 测试：
http://eureka7001.com:7001/ #查看Eureka中服务的名称  <br />
http://localhost/consumer/dept/get/1 #正常 <br />
http://localhost/consumer/dept/get/200 #异常，调用熔断器中的方法

# 2018-9-25 20:31:57 Hystrix-服务降级
## 测试：
### 开启服务：microservicecloud-eureka-7001、microservicecloud-eureka-7002、microservicecloud-eureka-7003、microservicecloud-provider-dept-8001
http://eureka7001.com:7001/ #查看Eureka中服务的名称  <br />
http://localhost/consumer/dept/get/1 #正常 <br />
### 此时停止服务提供者 microservicecloud-provider-dept-8001
http://localhost/consumer/dept/get/2 #服务停止时，调用熔断器工厂中的方法

# 2018-9-25 21:33:00 Hystrix-Dashboard监控Hystrix服务
## 测试：
### 开启服务：microservicecloud-eureka-7001、microservicecloud-eureka-7002、microservicecloud-eureka-7003、microservicecloud-provider-dept-hystrix-8001、microservicecloud-consumer-hystrix-dashboard
http://localhost:9001/hystrix #添加监控的服务 <br />
http://localhost:8001/hystrix.stream ，Delay：2000，Title：demo01 <br />
http://localhost:8001/dept/get/2 #不停刷新这个界面，然后看监控页面的信息