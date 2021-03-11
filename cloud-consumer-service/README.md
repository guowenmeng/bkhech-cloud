# Getting Started

### Reference Documentation

### Guides

### Attention
1. 当设置 feign.hystrix.enabled=true，启用 feign-hystrix 时会导致 feign 的超时设置失效
   # feign 超时设置。
   #feign:
   #  client:
   #    config:
   #      default:
   #        loggerLevel: basic #日志等级
   #        connectTimeout: 3000 #连接超时
   #        readTimeout: 3000 #读取超时
     # 开启断路器（熔断器）
   feign:  
     hystrix:
       enabled: true