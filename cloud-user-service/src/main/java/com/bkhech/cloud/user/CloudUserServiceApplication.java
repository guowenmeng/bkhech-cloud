package com.bkhech.cloud.user;

import com.bkhech.cloud.user.web.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@Import(JwtConfig.class)
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.bkhech")
public class CloudUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUserServiceApplication.class, args);
    }

}
