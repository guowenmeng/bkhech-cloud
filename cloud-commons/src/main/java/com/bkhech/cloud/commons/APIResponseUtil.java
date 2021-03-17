package com.bkhech.cloud.commons;

/**
 * @author guowm
 * @date 2021/3/17
 */
public class APIResponseUtil {

    public static <T> APIResponse<T> success(T data) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(MessageTipsType.SUCCESS_OPERATION.getCode());
        response.setData(data);
        return response;
    }

    public static <T> APIResponse<T> error(String message) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(MessageTipsType.FAIL_OPERATION.getCode());
        response.setMessage(message);
        return response;
    }

    public static <T> APIResponse<T> error(int code, String message) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> APIResponse<T> error(MessageTipsType messageTipsType) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(messageTipsType.getCode());
        response.setMessage(messageTipsType.getMessage());
        return response;
    }

    public static <T> APIResponse<T> error(int code, String message, T data) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

}
