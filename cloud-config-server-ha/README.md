# Getting Started
### Reference Documentation
- 官方文档：https://docs.spring.io/spring-cloud-config/docs/2.2.7.RELEASE/reference/html/#_quick_start
- 参考：https://segmentfault.com/a/1190000018587707?utm_source=tag-newest

### Attention
1. http请求地址和资源文件映射如下:
   /{application}/{profile}[/{label}]
   /{application}-{profile}.yml
   /{label}/{application}-{profile}.yml
   /{application}-{profile}.properties
   /{label}/{application}-{profile}.properties

2. 高可用方式一般奇数台（最少3台）

3. 自动刷新 rabbitmq 安装： rabbitmq-install.sh
 默认用户名密码： guest/guest
 访问：http://localhost:15672 或者 http://ip:15672
 文档：https://hub.docker.com/_/rabbitmq
 
 查看用户：rabbitmqctl list_users
 查看用户权限：rabbitmqctl  list_user_permissions admin
 给用户设置权限：rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"

4. 刷新配置：curl -X POST http://localhost:8888/actuator/bus-refresh