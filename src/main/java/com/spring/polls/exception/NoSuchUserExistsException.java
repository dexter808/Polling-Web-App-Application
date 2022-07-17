package com.spring.polls.exception;

public class NoSuchUserExistsException extends RuntimeException{
    private String message;

    public NoSuchUserExistsException() {
    }

    public NoSuchUserExistsException(String message) {
        super(message);
        this.message = message;
    }
}
