package com.spring.polls.controller.helper;

import com.spring.polls.exception.user.UserPropertyValidationException;
import com.spring.polls.models.entities.User;

import java.util.regex.Pattern;

public class Validator {

    public static boolean isMatching(String value,String regexPattern){
        return Pattern.compile(regexPattern).matcher(value).matches();
    }
    //User Validations
    public static boolean stringEmptyOrNull(String s){
        return s==null||s.trim().isEmpty();
    }
    public static boolean validateUserNullValues(User user){
        if(stringEmptyOrNull(user.getEmail()))
            throw new UserPropertyValidationException("User email is required");
        if(stringEmptyOrNull(user.getEmail()))
            throw new UserPropertyValidationException("User email is required");
        if(stringEmptyOrNull(user.getFirstName()))
            throw new UserPropertyValidationException("User firsName is required");
        if(stringEmptyOrNull(user.getLastName()))
            throw new UserPropertyValidationException("User lastName is required");
        return true;
    }
    public static boolean validateEmail(String email){
        return (isMatching(email,"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"));
    }
    public static boolean passwordValidation(String password){
        return stringEmptyOrNull(password);
    }
    public static boolean validateUser(User user){
        validateUserNullValues(user);
        if(!validateEmail(user.getEmail()))
            throw new UserPropertyValidationException("User email invalid");
        if(passwordValidation(user.getPassword()))
            throw new UserPropertyValidationException("User password does not match minimum constraints");
        return true;
    }
}
