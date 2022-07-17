package com.spring.polls.exception.user;

public class UserPropertyValidationException extends RuntimeException{
    private String message;

    public UserPropertyValidationException() {
    }

    public UserPropertyValidationException(String message) {
        super(message);
        this.message = message;
    }
}
