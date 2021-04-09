package com.bkhech.cloud.gateway.server.filter.glabalfilter.authentication;

import com.bkhech.cloud.gateway.server.jwt.AuthService;
import com.bkhech.cloud.gateway.server.jwt.config.JwtConfig;
import com.bkhech.cloud.gateway.server.util.FilterResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 认证过滤器
 *
 * @author guowm
 * @date 2021/3/22
 */
@Slf4j
@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final AuthService authService;

    private final JwtConfig jwtConfig;

    private Set<String> skipAuthUrls = new HashSet<>(16);

    public AuthenticationFilter(AuthService authService, JwtConfig jwtConfig) {
        this.authService = authService;
        this.jwtConfig = jwtConfig;
        skipAuthUrls.addAll(jwtConfig.getSkipAuthUrls());
        skipAuthUrls.add(jwtConfig.getLoginUrl());
    }

    private String getActualUri(ServerHttpRequest request) {
        String requestURI = request.getPath().toString();
        if (!requestURI.startsWith("/")) {
            requestURI = "/" + requestURI;
        }
        return requestURI;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestURI = getActualUri(request);

        // 跳过不需要验证的路径
        if (!skipAuthUrls.contains(requestURI)) {
            // 获取 jwtToken
            List<String> jwtTokens = request.getHeaders().get("Authorization");

            // 没有token
            if (StringUtils.isEmpty(jwtTokens)) {
                return authError(exchange, "need to auth");
            } else {
                // 有token
                String jwtToken = jwtTokens.get(0);
                try {
                    authService.checkToken(jwtToken);
                } catch (ExpiredJwtException e) {
                    String errorMessage;
                    if (e.getMessage().contains("Allowed clock skew")) {
                        errorMessage = "认证过期";
                    } else {
                        errorMessage = "认证失败";
                    }
                    return authError(exchange, errorMessage);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    return authError(exchange, "认证失败");
                }
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 认证错误输出
     * @param exchange
     * @param errorMessage
     * @return
     */
    private Mono<Void> authError(ServerWebExchange exchange, String errorMessage) {
        // 请求就此阶段，不向下游请求
        return FilterResponseUtil.customError(exchange, HttpStatus.UNAUTHORIZED, errorMessage);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE - 1;
    }
}
