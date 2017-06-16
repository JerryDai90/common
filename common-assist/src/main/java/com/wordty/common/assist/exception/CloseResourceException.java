package com.wordty.common.assist.exception;


/**
 * 关闭出错异常类.
 *
 * @author jerry
 * @date 2017 -06-16 19:17:49
 */
@SuppressWarnings("serial")
public class CloseResourceException extends RuntimeException{
    public CloseResourceException() {
        super();
    }
    public CloseResourceException(String message) {
        super(message);
    }
    public CloseResourceException(Throwable cause) {
        super(cause);
    }
}
