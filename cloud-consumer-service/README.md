# Getting Started

### Reference Documentation

### Guides

### Attention
1. 当设置 feign.hystrix.enabled=true

2. 开启feign请求、响应压缩
```properties
feign:
  compression:
    request:
      enabled: true
      mime-types: text/xml,application/xml,application/json
      min-request-size: 2048
    response:
      enabled: true
```
