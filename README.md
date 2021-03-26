# bkhech-cloud

## 服务端口分配
cloud-eureka-server 8761
cloud-eureka-server-ha 8761 8762 8763
cloud-provider-service 8002 8003
cloud-provider-api provider 的 jar 包
cloud-customer-service 8004
cloud-config-server 8888
cloud-config-server-ha 8888 8889 8890
cloud-config-client 8005
cloud-zuul-server 9999
cloud-user-service 7777


## Q&A
1. Feign
openfeign(支持负载均衡，底层是httpClient调用，并非RPC)是一个声明式的RESTful客户端，
openfeign在我的理解中，由于RestTemplate用起来比较麻烦，于是在这个基础上进行了封装，使调用更加简单，更加清晰。

hystrix是一种保证服务稳定的组件，使服务不会因为某个服务崩溃导致整个应用崩溃。可以简单的理解为通用的异常处理。
hystrix用起来非常的简单，openfeign默认支持hystrix，只不过需要开启才行。

Feign 其实是一种包装，把复杂的 Http 请求包装成我们只需写一两个注解就可以搞定的地步。他底层使用的还是 Ribbon。