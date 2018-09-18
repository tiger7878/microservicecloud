#2018-9-18 23:23:51 添加Eureka集群配置
#修改host文件：C:\Windows\System32\drivers\etc添加以下内容
#SpringCloud的Eureka集群配置
127.0.0.1	eureka7001.com
127.0.0.1	eureka7002.com
127.0.0.1	eureka7003.com
#测试：
http://eureka7001.com:7001/
http://eureka7002.com:7002/
http://eureka7003.com:7003/