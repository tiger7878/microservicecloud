#2018-9-18 23:23:51 添加Eureka集群配置<br />
#修改host文件：C:\Windows\System32\drivers\etc添加以下内容<br />
#SpringCloud的Eureka集群配置<br />
127.0.0.1	eureka7001.com<br />
127.0.0.1	eureka7002.com<br />
127.0.0.1	eureka7003.com<br />
#测试：<br />
http://eureka7001.com:7001/<br />
http://eureka7002.com:7002/<br />
http://eureka7003.com:7003/<br />