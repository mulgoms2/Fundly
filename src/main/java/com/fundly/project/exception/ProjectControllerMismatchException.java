package com.fundly.project.exception;

public class ProjectControllerMismatchException extends RuntimeException{
    public ProjectControllerMismatchException() {
    }

    public ProjectControllerMismatchException(String message) {
        super(message);
    }

    public ProjectControllerMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectControllerMismatchException(Throwable cause) {
        super(cause);
    }

    public ProjectControllerMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
