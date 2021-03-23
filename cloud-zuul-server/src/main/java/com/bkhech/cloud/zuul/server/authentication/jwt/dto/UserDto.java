package com.bkhech.cloud.zuul.server.authentication.jwt.dto;

import lombok.Data;

/**
 * 用户Dto
 * @author guowm
 * @date 2021/3/22
 */
@Data
public class UserDto {

    private String userName;

    private String password;

}
