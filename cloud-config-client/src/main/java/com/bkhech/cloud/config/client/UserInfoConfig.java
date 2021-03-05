package com.bkhech.cloud.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author guowm
 * @date 2021/3/5
 */
@ConfigurationProperties(prefix = "userinfo")
public class UserInfoConfig {

    private String username;
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
