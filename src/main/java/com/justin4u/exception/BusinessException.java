package com.justin4u.exception;

/**
 * com.justin4u.exception
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-06-09</pre>
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
