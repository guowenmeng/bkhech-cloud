package com.bkhech.cloud.gateway.server.util;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.commons.APIResponseUtil;
import com.bkhech.cloud.commons.MessageTipsType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 响应工具类
 * @author guowm
 * @date 2021/4/9
 */
public class FilterResponseUtil {

    /**
     * ObjectMapper 线程安全
     */
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 自定义响应错误
     * @param exchange 请求
     * @param httpStatus 状态码
     * @param errorMessage 消息
     * @return
     */
    public static Mono<Void> customError(ServerWebExchange exchange, HttpStatus httpStatus, String errorMessage) {
        APIResponse apiResponse = APIResponseUtil.error(StringUtils.isEmpty(errorMessage) ? MessageTipsType.FAIL_OPERATION.getMessage() : errorMessage);
        return Mono.defer(() -> {
            byte[] bytes = new byte[0];
            try {
                bytes = objectMapper.writeValueAsBytes(apiResponse);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(httpStatus);
            response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            return response.writeWith(Flux.just(buffer));
        });
    }

}
