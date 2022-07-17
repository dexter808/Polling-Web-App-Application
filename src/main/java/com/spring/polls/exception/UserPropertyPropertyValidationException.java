package com.spring.polls.exception;

public class UserPropertyPropertyValidationException extends RuntimeException{
    private String message;

    public UserPropertyPropertyValidationException() {
    }

    public UserPropertyPropertyValidationException(String message) {
        super(message);
        this.message = message;
    }
}
