package com.fundly.project.exception;

public class ImageValidationException extends RuntimeException{
    public ImageValidationException(){
        super();
    }
    public ImageValidationException(String msg){
        super(msg);
    }
}
