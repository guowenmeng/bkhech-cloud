package com.bkhech.cloud.zuul.server;

import com.bkhech.cloud.zuul.server.authentication.jwt.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.bkhech.cloud")
@EnableDiscoveryClient
/**
 * @EnableZuulProxy 注解启用zuul
 */
@EnableZuulProxy
@Import(JwtConfig.class)
public class CloudZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudZuulServerApplication.class, args);
    }

}
