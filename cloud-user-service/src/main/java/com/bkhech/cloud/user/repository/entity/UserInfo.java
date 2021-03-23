package com.bkhech.cloud.user.repository.entity;

import lombok.Data;

/**
 * 用户Dto
 * @author guowm
 * @date 2021/3/22
 */
@Data
public class UserInfo {

    private Long userId;

    private String userName;

    private String password;

}
