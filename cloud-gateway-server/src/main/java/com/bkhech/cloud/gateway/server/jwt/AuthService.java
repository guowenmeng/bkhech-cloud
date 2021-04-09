package com.bkhech.cloud.gateway.server.jwt;

import com.bkhech.cloud.commons.auth.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 认证接口
 * @author guowm
 * @date 2021/3/22
 */
@Slf4j
@Component
public class AuthService {

    private final ObjectMapper objectMapper;

    public AuthService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public boolean checkToken(String jwtToken) throws Exception {
        return JwtUtil.checkToken(jwtToken, objectMapper);
    }
}
