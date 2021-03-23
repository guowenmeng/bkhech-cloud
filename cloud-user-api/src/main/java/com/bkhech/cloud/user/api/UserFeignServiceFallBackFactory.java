package com.bkhech.cloud.user.api;

import com.bkhech.cloud.commons.APIResponseUtil;
import com.bkhech.cloud.commons.MessageTipsType;
import com.bkhech.cloud.commons.SystemException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 托底函数，即回调函数，针对FeignService
 *
 * 如果需要访问到造成回退的具体原因，可以使用@FeignClient.注解的fallbackFactory属性
 * @author guowm
 * @date 2021/3/16
 */
@Slf4j
@Component //交给Spring管理，配置为组件
public class UserFeignServiceFallBackFactory implements FallbackFactory<UserFeignService> {
    /**
     * Returns an instance of the fallback appropriate for the given cause.
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public UserFeignService create(Throwable cause) {
        log.info("UserFeignService FallBackFactory exception:", cause);
        //业务异常托底方法
        if (cause instanceof SystemException) {
            return userRequest -> {
                String message = "UserFeignServiceFallBackFactory: " + userRequest + ": " + LocalDateTime.now() + ": " + cause.getMessage();
                return APIResponseUtil.error(message);
            };
        }

        //未知异常托底方法 如： 超时异常（HystrixTimeoutException）， 重试异常（RetryableException）等
        if (cause instanceof HystrixTimeoutException) {
            // 超时异常，可以能需要重试，服务提供方需要保证业务幂等性
            return userRequest -> {
                String message = "UserFeignServiceFallBackFactory: " + userRequest.toString() + ": " + LocalDateTime.now() + ": 超时异常";
                return APIResponseUtil.error(message);
            };
        }
        return userRequest -> {
            String message = "UserFeignServiceFallBackFactory: " + userRequest.toString() + ": " + LocalDateTime.now() + ": " + MessageTipsType.FAIL_OPERATION.getMessage();
            return APIResponseUtil.error(message);
        };
    }
}
