package com.fundly.project.exception;

public class FundingValidationException extends RuntimeException {
    public FundingValidationException(){
        super();
    }
    public FundingValidationException(String msg){
        super(msg);
    }

}
