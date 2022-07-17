package com.spring.polls.service.interfaces;


import com.spring.polls.models.entities.User;

public interface PollsUserService {
    //Get user by id
    User getUserById(Long id);
    User getUserByUsername(String username);

    //Create user
    void createUser(User user);

    //Update user
    boolean updateUser(User user);

    //Delete user
    boolean deleteUser(Long id);
    void usernameExist(String username);
    void emailExist(String email);
}
