package com.fundly.project.exception;

public class ProjectDoesntExistsException extends RuntimeException{
    public ProjectDoesntExistsException() {
    }

    public ProjectDoesntExistsException(String message) {
        super(message);
    }
}
