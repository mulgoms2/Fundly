package com.fundly.project.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
