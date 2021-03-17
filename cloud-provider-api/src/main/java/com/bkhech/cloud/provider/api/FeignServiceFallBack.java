package com.bkhech.cloud.provider.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author guowm
 * @date 2021/3/10
 */
@Slf4j
@Component //交给Spring管理，配置为组件
public class FeignServiceFallBack implements FeignService {
    /**
     * 托底函数，即回调函数，针对FeignService
     * @param name
     * @return
     */
    @Override
    public String providerGet(String name) {
        log.warn("FeignServiceFallBack : {}", name);
        return "FeignServiceFallBack: " + name + ": " + LocalDateTime.now();
    }
}
