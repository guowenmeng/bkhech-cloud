package com.bkhech.cloud.customer.feign;

import com.bkhech.cloud.provider.api.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenFeign rest controller
 * @author guowm
 * @date 2021/3/4
 */
@RestController
public class OpenFeignTestController {

    private final FeignService feignService;

    public OpenFeignTestController(FeignService feignService) {
        this.feignService = feignService;
    }

    @GetMapping("openfeign/get")
    public String req() {
        String result = feignService.producerGet("openfeign-client");
        return result;
    }
}
