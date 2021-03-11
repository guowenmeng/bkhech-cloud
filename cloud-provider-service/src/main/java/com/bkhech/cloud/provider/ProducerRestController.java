package com.bkhech.cloud.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
        // 测试 feign 调用超时
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = String.format("from %s: %s", name, serverPort);
        System.out.println(result);
        return result;
    }
}
