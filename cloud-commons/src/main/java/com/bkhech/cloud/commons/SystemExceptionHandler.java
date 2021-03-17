package com.bkhech.cloud.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guowm
 * @date 2021/3/17
 */
@ControllerAdvice
public class SystemExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public APIResponse handler(HttpServletResponse response, Exception ex) {
        SystemException exception = (SystemException) ex;
        LOGGER.error("SystemException occurs! The exception information is as follows: ", ex);
        return APIResponseUtil.error(exception.getCode(), exception.getMessage(), exception.getErrorCause());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public APIResponse globalHandler(HttpServletRequest request, Exception ex) {
        LOGGER.error("UnknownException occurs! Request URL = " + request.getRequestURI() +". The exception information is as follows: ", ex);
        return APIResponseUtil.error(-1, "系统繁忙，请稍后重试", ex.getMessage());
    }

}
