package com.spring.polls.exception.handlers;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.exception.user.NoSuchUserExistsException;
import com.spring.polls.exception.user.UserAlreadyExistsException;
import com.spring.polls.exception.user.UserPropertyValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoSuchUserExistsException.class)
    public ResponseEntity<ResponseObject> handleNoSuchUserException(NoSuchUserExistsException noSuchUserExistsException){
        System.out.println(noSuchUserExistsException.getMessage()+"---------Exception----------------");
        return new ResponseEntity<>(new ResponseObject(noSuchUserExistsException.getMessage()),HttpStatus.NO_CONTENT);
    }

    //@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseObject> handleUserAlreadyExistException(UserAlreadyExistsException userAlreadyExistsException){
        return new ResponseEntity<>(new ResponseObject(userAlreadyExistsException.getMessage()),HttpStatus.CONFLICT);
    }

    //@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Testing")
    @ExceptionHandler(UserPropertyValidationException.class)
    public ResponseEntity<ResponseObject> handleUserPropertyException(UserPropertyValidationException userPropertyValidationException){
        return new ResponseEntity<>(new ResponseObject(userPropertyValidationException.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
