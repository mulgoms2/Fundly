package com.fundly.user.exception;

public class LikeDataAccessException extends Exception{

    public LikeDataAccessException() {}

    public LikeDataAccessException(String message, Exception e) {
        super(e + ":"  + message);
    }
}
