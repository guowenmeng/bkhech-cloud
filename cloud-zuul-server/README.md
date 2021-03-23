# Getting Started

### Reference Documentation
> [官网文档](https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#router-and-filter-zuul)
> [案例1: 用户权限验证](https://blog.csdn.net/qq_40407127/article/details/109449350)

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
2. filter type 有几种？
过滤器类型
Zuul 中的过滤器跟我们之前使用的 javax.servlet.Filter 不一样，javax.servlet.Filter 只有一种类型，
可以通过配置 urlPatterns 来拦截对应的请求。而 Zuul 中的过滤器总共有 4 种类型，且每种类型都有对应的使用场景。
1）pre
可以在请求被路由之前调用。适用于身份认证的场景，认证通过后再继续执行下面的流程。
2）route
在路由请求时被调用。适用于灰度发布场景，在将要路由的时候可以做一些自定义的逻辑。
3）post
在 route 和 error 过滤器之后被调用。这种过滤器将请求路由到达具体的服务之后执行。适用于需要添加响应头，记录响应日志等应用场景。
4）error
处理请求时发生错误时被调用。在执行过程中发送错误时会进入 error 过滤器，可以用来统一记录错误信息。