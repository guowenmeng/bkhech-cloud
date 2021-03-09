package com.bkhech.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudProducerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProducerServiceApplication.class, args);
    }

}
