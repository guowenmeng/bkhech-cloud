package com.bkhech.cloud.user.api;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.user.api.request.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign service
 * @author guowm
 * @date 2021/3/4
 * @FeignClient中参数：
 * name 为服务提供方在Eureka中注册的服务;
 * path 等价于 @RequestMapping("user");
 * fallback 为熔断后指定的异常处。优先于fallbackFactory
 * fallbackFactory: 如果需要访问到造成回退的具体原因，可以使用@FeignClient.注解的fallbackFactory属性
 */
@FeignClient(name = "user-service", path = "user", configuration = UserFeignExceptionConfiguration.class, fallbackFactory = UserFeignServiceFallBackFactory.class)
public interface UserFeignService {

    @PostMapping("/login")
    APIResponse<String> login(@RequestBody UserRequest userRequest);
}
