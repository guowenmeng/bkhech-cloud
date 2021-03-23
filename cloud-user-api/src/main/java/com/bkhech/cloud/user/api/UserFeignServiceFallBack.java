package com.bkhech.cloud.user.api;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.commons.APIResponseUtil;
import com.bkhech.cloud.user.api.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author guowm
 * @date 2021/3/10
 */
@Slf4j
@Component //交给Spring管理，配置为组件
public class UserFeignServiceFallBack implements UserFeignService {

    /**
     * 托底函数，即回调函数
     * @see UserFeignService
     * @param userRequest
     * @return
     */
    @Override
    public APIResponse<String> login(UserRequest userRequest) {
        log.warn("UserFeignServiceFallBack : {}", userRequest.toString());
        String result = "UserFeignServiceFallBack: " + userRequest.toString() + ": " + LocalDateTime.now();
        return APIResponseUtil.error(result);
    }
}
