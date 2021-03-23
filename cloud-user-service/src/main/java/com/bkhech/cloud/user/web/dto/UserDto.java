package com.bkhech.cloud.user.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 用户Dto
 * @author guowm
 * @date 2021/3/22
 */
@Data
public class UserDto {

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
