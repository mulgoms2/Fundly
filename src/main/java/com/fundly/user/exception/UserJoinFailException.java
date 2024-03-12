package com.fundly.user.exception;

public class UserJoinFailException extends RuntimeException {
    public UserJoinFailException() {
        super();
    }

    public UserJoinFailException(String message) {
        super(message);
    }

    public UserJoinFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserJoinFailException(Throwable cause) {
        super(cause);
    }

    protected UserJoinFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
