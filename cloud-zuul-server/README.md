# Getting Started

### Reference Documentation
> [官网文档](https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#router-and-filter-zuul)

### Guides
- 避免了为所有后端 独立管理CORS 和 身份验证 问题的需求

### Attention
1. 设置自定义访问超时时间
springCloud通过网关服务访问分布式内部服务，如果需要自定义访问超时时间,
由于网关内部是通过ribbon来进行服务负载，所有需要配置ribbon

错误：浏览器通过网关调用服务的请求，出现了504的状态错误。
原因：网关默认的等待时间为1秒，时间到了还没有响应就会报错。
设置：
```java
ribbon:
  eureka:
    enabled: true
  ReadTimeout: 7000
  ConnectTimeout: 7000
```
