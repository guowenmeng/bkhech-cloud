package com.bkhech.cloud.config.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guowm
 * @date 2021/3/5
 */
@RestController
@RequestMapping("config")
public class ConfigTestController {

    private final UserInfoConfig userInfoConfig;

    public ConfigTestController(UserInfoConfig userInfoConfig) {
        this.userInfoConfig = userInfoConfig;
    }

    @GetMapping("userInfo")
    private UserInfoConfig getUserInfo() {
        return userInfoConfig;
    }
}
