package com.bkhech.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 消费者集合
 * @author guowm
 * @date 2021/3/4
 */
@SpringBootApplication
// 开发服务注册与发现客户端
@EnableDiscoveryClient
/**
 * 激活feign
 * basePackages = "com.bkhech.cloud.producer.api" 扫描提供者提供(cloud-provider-api)的 api
 */
@EnableFeignClients(basePackages = "com.bkhech.cloud.provider.api")

/**
 * @EnableCircuitBreaker 激活 hystrix
 * com.bkhech.cloud.provider.api: 扫描提供者提供(cloud-provider-api)中的 bean 到 IOC 中
 * com.bkhech.cloud.consumer: 扫描本项目包目录
 */
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.bkhech.cloud.consumer", "com.bkhech.cloud.provider.api"})
public class CloudConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerServiceApplication.class, args);
    }

}
