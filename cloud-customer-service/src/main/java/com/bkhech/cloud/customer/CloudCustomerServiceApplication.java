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
@EnableDiscoveryClient // 开发服务注册与发现客户端
@EnableFeignClients(basePackages = "com.bkhech.cloud.customer.feign") // 开启feign客户端
public class CloudCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCustomerServiceApplication.class, args);
    }

}
