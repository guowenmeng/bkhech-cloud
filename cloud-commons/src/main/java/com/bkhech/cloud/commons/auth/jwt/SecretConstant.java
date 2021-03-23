package com.bkhech.cloud.commons.auth.jwt;

/**
 * JWT使用常量值
 * @author guowm
 * @date 2021/3/22
 */
public class SecretConstant {

    //签名秘钥 自定义
    public static final String BASE64_SECRET = "1234567890";

    //超时毫秒数（默认30分钟）
    public static final int EXPIRES_SECOND = 1800000;

    //用于JWT加密的密匙 自定义
    public static final String DATA_KEY = "0987654321";

}
