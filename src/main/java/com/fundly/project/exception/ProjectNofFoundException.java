package com.fundly.project.exception;

public class ProjectNofFoundException extends RuntimeException {
    public ProjectNofFoundException() {
    }

    public ProjectNofFoundException(String message) {
        super(message);
    }
}
