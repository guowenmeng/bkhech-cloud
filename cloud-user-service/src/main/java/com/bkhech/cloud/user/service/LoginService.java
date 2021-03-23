package com.bkhech.cloud.user.service;

import com.bkhech.cloud.commons.auth.jwt.JwtModel;
import com.bkhech.cloud.commons.auth.jwt.JwtUtil;
import com.bkhech.cloud.user.repository.entity.UserInfo;
import com.bkhech.cloud.user.web.config.JwtConfig;
import com.bkhech.cloud.user.web.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 认证接口
 * @author guowm
 * @date 2021/3/22
 */
@Slf4j
@Component
public class LoginService {

    private final ObjectMapper objectMapper;

    private final JwtConfig jwtConfig;

    public LoginService(ObjectMapper objectMapper, JwtConfig jwtConfig) {
        this.objectMapper = objectMapper;
        this.jwtConfig = jwtConfig;
    }

    /**
     * 创建 jwt
     * @param userInfo
     * @param jwtModel
     * @return jwt token
     * @throws JsonProcessingException
     */
    public String createJWT(UserInfo userInfo, JwtModel jwtModel) throws JsonProcessingException {
        return JwtUtil.createJWT(String.valueOf(userInfo.getUserId()), userInfo.getUserName(), objectMapper.writeValueAsString(jwtModel), jwtConfig.getEffectiveTime().toMillis());
    }

    public boolean checkToken(String jwtToken) throws Exception {
        return JwtUtil.checkToken(jwtToken, objectMapper);
    }
}
