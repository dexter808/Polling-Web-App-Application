package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollRepositoryTest {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private UserRepository userRepository;

}