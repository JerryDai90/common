package com.wordty.common.assist.exception;


/**
 * 提示错误异常类.
 *
 * @author jerry
 * @date 2017 -06-16 19:22:31
 */
public class TipException extends RuntimeException {

    /**
     * 错误代码.
     */
    private String errorCode;
    /**
     * 错误具体信息.
     */
    private String errorInfo;
    /**
     * 错误栈信息.
     */
    private Throwable cause;

    public TipException() {
        super();
    }
    public TipException(String errorCode, String errorInfo) {
        super(errorInfo);
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }
    public TipException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
