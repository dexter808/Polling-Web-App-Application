package com.spring.polls.exception.poll;

public class PollAlreadyExistException extends RuntimeException{
    private String message;

    public PollAlreadyExistException(String message) {
        this.message = message;
    }

    public PollAlreadyExistException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
