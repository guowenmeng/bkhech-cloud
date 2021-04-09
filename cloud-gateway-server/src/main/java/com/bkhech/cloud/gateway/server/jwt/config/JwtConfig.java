package com.bkhech.cloud.gateway.server.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * @author guowm
 * @date 2021/3/22
 */
@Data
@ConfigurationProperties(prefix = "bkhech.jwt")
public class JwtConfig {

    private String loginUrl = "/login";

    private Set<String> skipAuthUrls = new HashSet<>(16);

}
