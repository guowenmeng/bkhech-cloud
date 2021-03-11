package com.bkhech.cloud.consumer.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置
 * @see LoadBalanced
 * @see IRule
 * @author guowm
 * @date 2021/3/4
 */
@Configuration(proxyBeanMethods = false)
public class RibbonRestTemplateConfig {

    /**
     * 负载均衡注解，通过restTemplate 发送的请求默认带有负载均衡的特性(轮询)
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 针对全局进行配置, 优先级最高
     * @return
     */
    @Bean
    public IRule ribbonRule(){
        return new RoundRobinRule();
    }


}
