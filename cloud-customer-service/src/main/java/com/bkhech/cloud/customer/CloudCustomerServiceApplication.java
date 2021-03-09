package com.bkhech.cloud.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消费者集合
 * @author guowm
 * @date 2021/3/4
 */
@SpringBootApplication
// 开发服务注册与发现客户端
@EnableDiscoveryClient
/**
 * 开启feign客户端
 * basePackages = "com.bkhech.cloud.producer.api" 扫描提供者提供(cloud-producer-api)的 api
 */
@EnableFeignClients(basePackages = "com.bkhech.cloud.provider.api")
public class CloudCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCustomerServiceApplication.class, args);
    }

}
