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
2. 报错：w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.lang.String` out of START_ARRAY token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.lang.String` out of START_ARRAY token
    at [Source: (PushbackInputStream); line: 1, column: 315] (through reference chain: java.util.LinkedHashMap["commits"])]
    