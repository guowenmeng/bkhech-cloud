package com.bkhech.cloud.provider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign service
 * @author guowm
 * @date 2021/3/4
 * @FeignClient中参数：
 * name 为服务提供方在Eureka中注册的服务;
 * path 等价于 @RequestMapping("producer");
 * fallback 为熔断后指定的异常处
 */

@FeignClient(name = "provider-service", path = "provider", fallback = FeignServiceFallBack.class)
public interface FeignService {

    /**
     * 调用feign provider
     * @return
     */
    @GetMapping("get")
    String providerGet(@RequestParam String name);
}
