package com.bkhech.cloud.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guowm
 * @date 2021/3/17
 */
@Slf4j
@ControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnMissingBean(SystemExceptionHandler.class)
@ResponseBody
public class SystemExceptionHandler {

    /**
     * 处理业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public APIResponse<String> handler(Exception ex) {
        SystemException exception = (SystemException) ex;
        log.error("SystemException occurs! The exception information is as follows: ", ex);
        return APIResponseUtil.error(exception.getCode(), exception.getMessage(), "");
    }

    /**
     * 未知异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public APIResponse<String> handleUnknownException(HttpServletRequest request, Exception ex) {
        log.error("UnknownException occurs! Request URL = " + request.getRequestURI() +". The exception information is as follows: ", ex);
        return APIResponseUtil.error(MessageTipsType.FAIL_OPERATION.getCode(), "系统繁忙，请稍后重试", ex.getMessage());
    }

    /**
     * 处理接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public APIResponse<String> handleBeanPropertyBindingResult(BindException e) {
        return APIResponseUtil.error(buildMessageFromException(e.getFieldErrors()));
    }

    /**
     * 处理接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public APIResponse<String> handleArgumentNotValid(MethodArgumentNotValidException  e) {
        return APIResponseUtil.error(buildMessageFromException(e.getBindingResult().getFieldErrors()));
    }

    private String buildMessageFromException(List<FieldError> fieldErrors) {
        return fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
    }

    /**
     * 处理接口数据验证异常,请求方式异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class, IllegalArgumentException.class, HttpRequestMethodNotSupportedException.class})
    public APIResponse<String> handleMissingServletRequestParameter(Exception e) {
        return APIResponseUtil.error(e.getMessage());
    }


    /**
     * MaxUploadSizeExceededException
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public APIResponse<String> handleAuthorizationException(MaxUploadSizeExceededException e) {
        return APIResponseUtil.error(MessageTipsType.MAX_UPLOAD_SIZE_EXCEEDED);
    }

}
