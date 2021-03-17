package com.bkhech.cloud.commons;

/**
 * @author guowm
 * @date 2021/3/17
 */
public class AuthorizeException extends RuntimeException {

    public AuthorizeException() {
        super();
    }

    public AuthorizeException(String message) {
        super(message);
    }
}
