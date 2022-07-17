package com.spring.polls.service;

import com.spring.polls.controller.helper.Validator;
import com.spring.polls.exception.user.NoSuchUserExistsException;
import com.spring.polls.exception.user.UserAlreadyExistsException;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.repositories.UserRepository;
import com.spring.polls.service.interfaces.PollsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements PollsUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        if(!userRepository.existsById(id))
            throw new NoSuchUserExistsException("User with user_id does not exist");
        else
            return userRepository.getById(id);
    }
    @Override
    public User getUserByUsername(String username){
        if(!userRepository.existsUserByUsername(username))
            throw new NoSuchUserExistsException("User does not exist with the username");
        return userRepository.findByUsername(username);
    }
    @Override
    public void createUser(User user) {
        Validator.validateUser(user);
        usernameExist(user.getUsername());
        emailExist(user.getEmail());
        userRepository.save(user);
    }

    public void emailExist(String email) {
        if(userRepository.existsUserByEmail(email))
            throw new UserAlreadyExistsException("User exist with the same email");
    }

    public void usernameExist(String username) {
        if(userRepository.existsUserByUsername(username))
            throw new UserAlreadyExistsException("User exist with the same username");
    }

    //Cannot update password using this function
    @Override
    public boolean updateUser(User user) {
        Validator.validateUser(user);
        userRepository.updateUserInfoById(user.getFirstName(),user.getLastName(), user.getPhn(), user.getEmail(),user.getUsername(),user.getId());
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepository.delete(getUserById(id));
        return true;
    }
}
