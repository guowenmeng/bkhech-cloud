package com.bkhech.cloud.config.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 解决：
 * .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize instance of `java.lang.String` out of START_ARRAY token; nested exception is com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.lang.String` out of START_ARRAY token
 *  at [Source: (PushbackInputStream); line: 1, column: 315] (through reference chain: java.util.LinkedHashMap["commits"])]
 * @author guowm
 * @date 2021/3/9
 */
@Slf4j
@RestController
public class BusRefreshController {

    private final RestTemplate restTemplate = new RestTemplate();
    final String URL = "http://sd8hb9.natappfree.cc/actuator/bus-refresh";


    @PostMapping("/busRefresh")
    public String busRefresh() {
        Map<String, String> map = new HashMap(2);
        restTemplate.postForObject(URL, map, Object.class);
        return "success";
    }

}
