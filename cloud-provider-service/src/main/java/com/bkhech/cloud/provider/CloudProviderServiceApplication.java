package com.bkhech.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.bkhech.cloud.provider", "com.bkhech.cloud.commons"})
@EnableDiscoveryClient
public class CloudProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderServiceApplication.class, args);
    }

}
