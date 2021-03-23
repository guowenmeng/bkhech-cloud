package com.bkhech.cloud.user.web.controller;

import com.bkhech.cloud.commons.APIResponse;
import com.bkhech.cloud.commons.APIResponseUtil;
import com.bkhech.cloud.commons.auth.jwt.JwtModel;
import com.bkhech.cloud.user.constants.UserConstant;
import com.bkhech.cloud.user.repository.entity.UserInfo;
import com.bkhech.cloud.user.service.LoginService;
import com.bkhech.cloud.user.web.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * 登陆控制器
 * @author guowm
 * @date 2021/3/22
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping("/login")
    public APIResponse<String> login(@Valid @RequestBody UserDto userDto) {
        if (userDto.getUserName().equals(UserConstant.USER_NAME) && userDto.getPassword().equals(UserConstant.PASSWORD)) {
            // TODO 根据实际业务，调取用户服务获取用户信息
            // 模拟数据
            ArrayList<String> roleIdList = new ArrayList<>(1);
            roleIdList.add("role_test_1");
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(userDto, userInfo);
            userInfo.setUserId(System.nanoTime());


            try {
                JwtModel jwtModel = new JwtModel(userDto.getUserName(), roleIdList);
                String jwt = loginService.createJWT(userInfo, jwtModel);
                return APIResponseUtil.success(jwt);
            } catch (JsonProcessingException e) {
                log.error("create jwt token fail.", e);
                return APIResponseUtil.success("login fail");
            }
        }
        return APIResponseUtil.error("username or password is error");
    }

}
