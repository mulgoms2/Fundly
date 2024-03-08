package com.fundly.project.exception;

import java.io.IOException;

public class ImageSaveFailureException extends RuntimeException {

    public ImageSaveFailureException() {
        super();
    }

    public ImageSaveFailureException(String message) {
        super(message);
    }

    public ImageSaveFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageSaveFailureException(Throwable cause) {
        super(cause);
    }

    protected ImageSaveFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}