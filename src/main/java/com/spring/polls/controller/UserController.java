package com.spring.polls.controller;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.controller.helper.Validator;
import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.controller.pojo.UserInfo;
import com.spring.polls.controller.pojo.UserRegister;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import com.spring.polls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @PostMapping(value = "/user/create")
    public ResponseEntity<ResponseObject<String>> createUser(@RequestBody UserRegister userRegister){
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        User user=new User(userRegister);
        userService.createUser(user);
        return new ResponseEntity<>(
                new ResponseObject<String>("User Created",
                "DONE"),
                HttpStatus.OK
        );
    }
    @GetMapping(value = "/user/{username}")
    public ResponseEntity<ResponseObject<UserInfo>> readUser(@PathVariable String username){
        return new ResponseEntity<>(
                new ResponseObject<UserInfo>(
                        "User Found",
                        new UserInfo(userService.getUserByUsername(username))),
                HttpStatus.OK
        );
    }

    @GetMapping("/user/poll/{username}")
    public ResponseEntity<ResponseObject<ArrayList<PollInfo>>> readPoll(@PathVariable String username){
        ArrayList<PollInfo> an=new ArrayList<>();
        for(Poll poll:userService.getUserByUsername(username).getPollList()){
            if(!(poll.isPrivate()))
                an.add(new PollInfo(poll));
        }
        return new ResponseEntity<>(
                new ResponseObject<ArrayList<PollInfo>>(
                        "Polls fetched",
                        an
                ),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/user")
    public ResponseEntity<ResponseObject<String>> updateUser(@RequestBody UserInfo userInfo, Principal principal){
        if(!Validator.stringEmptyOrNull(userInfo.getEmail()))
            userService.emailExist(userInfo.getEmail());
        if(!Validator.stringEmptyOrNull(userInfo.getUsername()))
            userService.usernameExist(userInfo.getUsername());
        User user=userService.getUserByUsername(principal.getName());
        user.fillProperty(userInfo);
        userService.updateUser(user);
        return new ResponseEntity<>(
                new ResponseObject<String>("User updated",
                        "DONE"),
                HttpStatus.OK
        );
    }
    @DeleteMapping(value = "/user")
    public ResponseEntity<ResponseObject<String>> deleteUser(Principal principal){
        userService.deleteUser(userService.getUserByUsername(principal.getName()).getId());
        return new ResponseEntity<>(
                new ResponseObject<String>(
                        "User Deleted",
                        "DONE"
                ),
                HttpStatus.OK
        );
    }
}
