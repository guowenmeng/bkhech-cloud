package com.bkhech.cloud.user.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author guowm
 * @date 2021/3/22
 */
@Data
@ConfigurationProperties(prefix = "bkhech.jwt")
public class JwtConfig {

    private Duration effectiveTime;

}