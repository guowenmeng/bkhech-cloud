package com.bkhech.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudConfigServerHaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerHaApplication.class, args);
    }

}
