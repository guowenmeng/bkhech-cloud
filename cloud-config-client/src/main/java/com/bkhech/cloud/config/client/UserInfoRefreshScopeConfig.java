package com.bkhech.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author guowm
 * @date 2021/3/5
 */
@RefreshScope
@Configuration
public class UserInfoRefreshScopeConfig {

    @Value("${userinfo.username}")
    private String username;

    @Value("${userinfo.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
