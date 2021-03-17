package com.bkhech.cloud.provider;

import com.bkhech.cloud.commons.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author guowm
 * @date 2021/3/4
 */
@Slf4j
@RestController
@RequestMapping("provider")
public class ProducerRestController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("get")
    public String producerGet(@RequestParam String name) {
        int timeSecond = ThreadLocalRandom.current().nextInt(2, 7);
        log.warn("sleep time: {}", timeSecond);
        // 测试 feign 调用，provider 出现异常, 使用 feign.hystrix.FallbackFactory 捕获异常
        if (timeSecond % 2 != 0) {
            throw new SystemException("provider 出现异常");
        }

        // 测试 feign 调用超时
        try {
            TimeUnit.SECONDS.sleep(timeSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = String.format("from %s: %s", name, serverPort);
        System.out.println(result);
        return result;

    }
}
