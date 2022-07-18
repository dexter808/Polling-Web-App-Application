package com.spring.polls.controller.helper;

public class ResponseObject<Result> {
    String message;
    Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ResponseObject(String message, Result result) {
        this.message = message;
        this.result = result;
    }

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
