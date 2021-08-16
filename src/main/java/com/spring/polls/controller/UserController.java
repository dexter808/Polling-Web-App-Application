package com.spring.polls.controller;

import com.spring.polls.controller.helper.UserUpdate;
import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.controller.pojo.UserInfo;
import com.spring.polls.controller.pojo.UserRegister;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUpdate userUpdate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping(value = "/user/create")
    public void createUser(@RequestBody UserRegister userRegister){
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        User user=new User(userRegister);
        userRepository.save(user);
    }
    @GetMapping(value = "/user/{username}")
    public UserInfo readUser(@PathVariable String username){
        return new UserInfo(userRepository.findByUsername(username));
    }
    @GetMapping("/user/poll/{username}")
    public ArrayList<PollInfo> readPoll(@PathVariable String username){
        ArrayList<PollInfo> an=new ArrayList<>();
        for(Poll poll:userRepository.findByUsername(username).getPollList()){
            if(!(poll.isPrivate()))
                an.add(new PollInfo(poll));
        }
        return an;
    }
    @PutMapping(value = "/user")
    public void updateUser(@RequestBody UserInfo userInfo, Principal principal){
        userUpdate.infoUpdate(userInfo,userRepository.findByUsername(principal.getName()));
    }
    @DeleteMapping(value = "/user")
    public void deleteUser(Principal principal){
        userRepository.delete(userRepository.findByUsername(principal.getName()));
    }
}
