package com.fundly.user.exception;

public class UserProfileFailException extends RuntimeException {
    public UserProfileFailException() {
        super();
    }

    public UserProfileFailException(String message) {
        super(message);
    }

    public UserProfileFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserProfileFailException(Throwable cause) {
        super(cause);
    }

    protected UserProfileFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
