package com.bkhech.cloud.config.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guowm
 * @date 2021/3/5
 */
@Slf4j
@RestController
@RequestMapping("config")
public class ConfigTestController {

    private final UserInfoConfigurationPropertiesConfig userInfoConfigurationPropertiesConfig;

    private final UserInfoRefreshScopeConfig userInfoRefreshScopeConfig;


    public ConfigTestController(UserInfoConfigurationPropertiesConfig userInfoConfigurationPropertiesConfig, UserInfoRefreshScopeConfig userInfoRefreshScopeConfig) {
        this.userInfoConfigurationPropertiesConfig = userInfoConfigurationPropertiesConfig;
        this.userInfoRefreshScopeConfig = userInfoRefreshScopeConfig;
    }

    @GetMapping("userInfo")
    private UserInfoConfigurationPropertiesConfig getUserInfo() {
        log.info("userInfoRefreshScopeConfig userName : {}, password: {}", userInfoRefreshScopeConfig.getUsername(), userInfoRefreshScopeConfig.getPassword());
        log.info("userInfoConfigurationPropertiesConfig userName : {}, password: {}", userInfoConfigurationPropertiesConfig.getUsername(), userInfoConfigurationPropertiesConfig.getPassword());
        return userInfoConfigurationPropertiesConfig;
    }
}
