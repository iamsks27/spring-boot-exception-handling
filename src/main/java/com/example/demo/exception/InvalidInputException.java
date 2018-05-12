package com.example.demo.exception;

public class InvalidInputException extends RuntimeException {

    private String errorMsg;
    private Integer errorCode;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(final String errorMsg, final Integer errorCode) {
        super();
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "InvalidInputException{" +
                "errorMsg='" + errorMsg + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
