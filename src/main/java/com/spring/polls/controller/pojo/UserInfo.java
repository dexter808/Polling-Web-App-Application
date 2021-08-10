package com.spring.polls.controller.pojo;

import com.spring.polls.models.entities.User;

public class UserInfo {
    private String username;
    private String name;
    private String email;

    public UserInfo(User user) {
        username=user.getUsername();
        name=user.getFirstName()+user.getLastName();
        email=user.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
