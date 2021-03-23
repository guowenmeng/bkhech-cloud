package com.bkhech.cloud.user.api.request;

import lombok.Data;

/**
 * 用户Dto
 * @author guowm
 * @date 2021/3/22
 */
@Data
public class UserRequest {

    private String userName;

    private String password;

}
