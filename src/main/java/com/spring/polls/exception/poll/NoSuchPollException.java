package com.spring.polls.exception.poll;

public class NoSuchPollException extends RuntimeException{
    private String message;

    public NoSuchPollException(String message) {
        this.message = message;
    }

    public NoSuchPollException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
