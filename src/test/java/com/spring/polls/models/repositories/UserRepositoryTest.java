package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void username(){
        userRepository.save(new User("user","dsd","dsds","lkjdslad","username2",passwordEncoder.encode("password2")));
    }
}