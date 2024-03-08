package com.fundly.user.exception;

public class LikeNotFoundException extends Exception{

    public LikeNotFoundException() {}

    public LikeNotFoundException(String message, Exception e) {
        super(e + ":"  + message);
    }
}
