package com.fundly.project.exception;

public class ProjectUpdateFailureException extends RuntimeException {
    public ProjectUpdateFailureException() {
        super();
    }

    public ProjectUpdateFailureException(String message) {
        super(message);
    }

    public ProjectUpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectUpdateFailureException(Throwable cause) {
        super(cause);
    }

    protected ProjectUpdateFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
