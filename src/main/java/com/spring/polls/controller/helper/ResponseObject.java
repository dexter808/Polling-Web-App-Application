package com.spring.polls.controller.helper;

public class ResponseObject {
    String message;

    public ResponseObject(){
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseObject(String message) {
        this.message = message;
    }
}
