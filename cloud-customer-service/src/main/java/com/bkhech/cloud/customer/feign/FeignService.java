package com.bkhech.cloud.customer.feign;

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

@FeignClient(name = "producer-service", path = "producer")
public interface FeignService {

    /**
     * 调用feign producer
     * @return
     */
    @GetMapping("get")
    String producerGet(@RequestParam String name);
}
