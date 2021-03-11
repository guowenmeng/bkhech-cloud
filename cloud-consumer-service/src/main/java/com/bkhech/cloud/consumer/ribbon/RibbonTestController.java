package com.bkhech.cloud.consumer.ribbon;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon rest controller
 * @author guowm
 * @date 2021/3/4
 */
@RestController
public class RibbonTestController {

    private final RestTemplate restTemplate;

    public RibbonTestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("ribbon/get")
    public String req() {
        String result = restTemplate.getForObject("http://producer-service/producer/get?name=ribbon-client", String.class);
        return result;
    }
}
