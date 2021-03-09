package com.bkhech.cloud.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guowm
 * @date 2021/3/4
 */
@RestController
@RequestMapping("provider")
public class ProducerRestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("get")
    public String producerGet(@RequestParam String name) {
        String result = String.format("from %s: %s", name, serverPort);
        System.out.println(result);
        return result;
    }
}
