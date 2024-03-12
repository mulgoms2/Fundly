package com.fundly.user.exception;

public class UserLoinFailException extends RuntimeException  {
    public UserLoinFailException() { super(); }

    public UserLoinFailException(String message) { super(message); }

    public UserLoinFailException(String message, Throwable cause) { super(message, cause); }

    public UserLoinFailException(Throwable cause) { super(cause); }

    protected UserLoinFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace); }
}
