package com.bkhech.cloud.commons;

import org.springframework.http.HttpStatus;

/**
 * @author guowm
 * @date 2021/3/17
 */
public enum MessageTipsType {
    SUCCESS_OPERATION(200, "操作成功"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未认证,请重新登陆"),
    MAX_UPLOAD_SIZE_EXCEEDED(-1, "上传文件过大"),
    FEIGN_ERROR(800, "feign_error"),
    FAIL_OPERATION(-1, "操作失败");

    private int code;

    private String message;

    MessageTipsType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
