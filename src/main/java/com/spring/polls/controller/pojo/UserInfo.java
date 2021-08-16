package com.spring.polls.controller.pojo;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.models.entities.User;

public class UserInfo extends ResponseObject {
    private String username;
    private String firstName,lastName;
    private String email,phoneNumber;
    public UserInfo(){
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserInfo(User user) {
        username=user.getUsername();
        firstName=user.getFirstName();
        lastName=user.getLastName();
        email=user.getEmail();
        phoneNumber=user.getPhn();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public void copyInfo(UserInfo userInfo){
        if(userInfo.firstName!=null)
            firstName=userInfo.firstName;
        if(userInfo.lastName!=null)
            firstName=userInfo.lastName;
        if(userInfo.username!=null)
            firstName=userInfo.username;
        if(userInfo.email!=null)
            firstName=userInfo.email;
        if(userInfo.phoneNumber!=null)
            firstName=userInfo.phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
