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

# 2018-9-25 22:11:44 Zuul网关
## 修改host文件：C:\Windows\System32\drivers\etc添加以下内容
#SpringCloud的Zuurl网关配置 <br />
127.0.0.1	myzuul.com
## 开启服务：
microservicecloud-eureka-7001、microservicecloud-eureka-7002、microservicecloud-eureka-7003、microservicecloud-provider-dept-8001、microservicecloud-zuul-gateway-9527
## 测试：
http://eureka7001.com:7001/ #查看Eureka中服务的名称  <br />
http://localhost:8001/dept/get/2 #查看不用路由的情况 <br />
http://myzuul.com:9527/microservicecloud-dept/dept/get/2 #使用路由的情况访问

# 2018-9-25 22:37:28 Zuul路由访问映射规则
## 开启服务：
microservicecloud-eureka-7001、microservicecloud-eureka-7002、microservicecloud-eureka-7003、microservicecloud-provider-dept-8001、microservicecloud-zuul-gateway-9527
## 测试：
http://myzuul.com:9527/monkey/mydept/dept/get/4 #查看服务是否正常

# 2018-9-27 22:14:03 Config统一配置中心-基础配置
## 在GitHub中创建一个项目：microservicecloud-config、然后获取到本地，然后创建一个application.yml提交到GitHub中
## 修改host文件：C:\Windows\System32\drivers\etc添加以下内容
#SpringCloud的Config网关配置 <br />
127.0.0.1	config-3344.com
## 开启服务：
microservicecloud-config-3344
## 测试：
http://config-3344.com:3344/application-dev.yml <br />
http://config-3344.com:3344/application-test.yml <br />
http://config-3344.com:3344/application-abc.yml

# 2018-9-30 21:01:12 Config客户端连接-Config服务端-配置文件在GitHub
## 开启服务：
microservicecloud-config-3344 <br />
microservicecloud-config-client-3355
## 测试(修改bootstrap.yml切换环境)
http://localhost:8201/config <br />
http://localhost:8202/config

# 2018-10-3 10:33:54 Config统一配置-Eureka Server和dept服务提供者
## 开启：
microservicecloud-config-3344 <br/>
microservicecloud-config-eureka-client-7001 <br/>
microservicecloud-config-dept-client-8001
## 测试：
http://eureka7001.com:7001/ #查看Eureka中的服务 <br/>
http://localhost:8001/dept/list #查看修改配置文件后的结果

# 2018-10-3 15:05:25 新增maven发布jar包到nexus私服中
## 注意：
先在本地maven仓库的settings.xml中配置私服的账号和密码相关信息 <br/>
然后再项目的pom.xml配置私服发布的地址和名称 <br/>
最后在开发工具中执行deploy命令发布到nexus <br/>
maven会判断版本后面是否带了-SNAPSHOT，如果带了就发布到snapshots仓库，否则发布到release仓库