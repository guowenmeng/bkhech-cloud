package com.bkhech.cloud.commons;

/**
 * @author guowm
 * @date 2021/3/17
 */
public enum MessageTipsType {
    SUCCESS_OPERATION(200, "操作成功"),
    FEIGN_ERROR(800, "feign_error"),
    FAIL_OPERATION(999, "操作失败");

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
