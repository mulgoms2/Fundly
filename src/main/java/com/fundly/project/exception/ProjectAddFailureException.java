package com.fundly.project.exception;

public class ProjectAddFailureException extends RuntimeException {
    public ProjectAddFailureException() {
    }

    public ProjectAddFailureException(String message) {
        super(message);
    }
}
