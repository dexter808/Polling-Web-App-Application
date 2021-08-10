package com.spring.polls.controller;

import com.spring.polls.controller.pojo.UserInfo;
import com.spring.polls.controller.pojo.UserRegister;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping(value = "/mainpage/user/{username}")
    public UserInfo mainuserInfo(@PathVariable String username, Principal principal){
        System.out.println(principal.getName()+"----------------");
        return new UserInfo(userRepository.findByUsername(username));
    }
    @GetMapping(value = "/")
    public String Home(){
        return "Home Reached";
    }
    @PostMapping(value = "/register")
    public String registration(@RequestBody UserRegister userRegister){
        System.out.println(userRegister+ "::::::::called");
        userRepository.save(new User(userRegister.getFirstName(),userRegister.getLastName(),userRegister.getEmail(),userRegister.getEmail(),userRegister.getUsername(),passwordEncoder.encode(userRegister.getPassword())));
        return "Added Successfully";
    }
}
