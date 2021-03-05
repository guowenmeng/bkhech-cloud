package com.bkhech.cloud.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign service
 * @author guowm
 * @date 2021/3/4
 */
@FeignClient(name = "producer-service")
@RequestMapping("producer")
public interface FeignService {

    /**
     * 调用feign producer
     * @return
     */
    @GetMapping("get")
    String producerGet(@RequestParam String name);
}
