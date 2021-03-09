package com.bkhech.cloud.provider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign service
 * @author guowm
 * @date 2021/3/4
 * @FeignClient中参数：
 * path 等价于 @RequestMapping("producer")
 */

@FeignClient(name = "provider-service", path = "provider")
public interface FeignService {

    /**
     * 调用feign producer
     * @return
     */
    @GetMapping("get")
    String producerGet(@RequestParam String name);
}
