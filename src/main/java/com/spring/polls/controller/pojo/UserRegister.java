package com.spring.polls.controller.pojo;

import javax.persistence.Column;

public class UserRegister {
    private String firstName;
    private String lastName;
    private String phn;
    private String email;
    private String username;
    private String password;

    public UserRegister() {
    }

    public UserRegister(String firstName, String lastName, String phn, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phn = phn;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
