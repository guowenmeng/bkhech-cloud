package com.bkhech.cloud.commons;

/**
 * @author guowm
 * @date 2021/3/17
 */
public class APIResponse<T> {

    private int code;

    private T data;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
