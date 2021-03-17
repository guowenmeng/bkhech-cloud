package com.bkhech.cloud.commons;

/**
 * @author guowm[guowm@5fun.com]
 * @date 2021/3/17
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1989404958319894453L;

    private int code;

    private Object errorCause;

    public SystemException() {
        super(MessageTipsType.FAIL_OPERATION.getMessage());
        this.code = MessageTipsType.FAIL_OPERATION.getCode();
        errorCause = "";
    }

    public SystemException(MessageTipsType messageTipsType) {
        super(messageTipsType.getMessage());
        this.code = messageTipsType.getCode();
    }

    public SystemException(String message) {
        super(message);
        this.code = MessageTipsType.FAIL_OPERATION.getCode();
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(int code, String message, Object errorCause) {
        super(message);
        this.code = code;
        this.errorCause = errorCause;
    }

    public SystemException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = MessageTipsType.FAIL_OPERATION.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(Object errorCause) {
        this.errorCause = errorCause;
    }
}
