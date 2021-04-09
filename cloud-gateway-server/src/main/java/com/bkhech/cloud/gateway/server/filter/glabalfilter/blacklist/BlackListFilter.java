package com.bkhech.cloud.gateway.server.filter.glabalfilter.blacklist;

import com.bkhech.cloud.gateway.server.util.FilterResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * ip黑名单过滤器
 * @author guowm
 * @date 2021/3/18
 */
@Slf4j
@Component
public class BlackListFilter implements GlobalFilter, Ordered {

    private static List<String> blackList;

    static {
        // 模拟黑名单 "0:0:0:0:0:0:0:1"
        blackList = Arrays.asList("192.168.71.23");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String remoteAddr = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        if (blackList.contains(remoteAddr)) {
            // 请求就此结束，不向下游请求
            log.info("remoteAddr [{}] is illegal", remoteAddr);
            return FilterResponseUtil.customError(exchange, HttpStatus.FORBIDDEN, "");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
