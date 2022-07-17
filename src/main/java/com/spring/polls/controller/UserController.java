package com.spring.polls.controller;

import com.spring.polls.controller.helper.Validator;
import com.spring.polls.controller.pojo.UserInfo;
import com.spring.polls.controller.pojo.UserRegister;
import com.spring.polls.models.entities.User;
import com.spring.polls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @PostMapping(value = "/user/create")
    public ResponseEntity<String> createUser(@RequestBody UserRegister userRegister){
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        User user=new User(userRegister);
        userService.createUser(user);
        return new ResponseEntity<>("User Created",HttpStatus.OK);
    }
    @GetMapping(value = "/user/{username}")
    public ResponseEntity<UserInfo> readUser(@PathVariable String username){
        ResponseEntity<UserInfo> userInfoResponseEntity = new ResponseEntity<>(new UserInfo(userService.getUserByUsername(username)),HttpStatus.OK);
        userInfoResponseEntity.getBody().setMessage("User Found");
        return userInfoResponseEntity;
    }

    //Implement later, closing for now
    /*@GetMapping("/user/poll/{username}")
    public ArrayList<PollInfo> readPoll(@PathVariable String username){
        ArrayList<PollInfo> an=new ArrayList<>();
        for(Poll poll:userRepository.findByUsername(username).getPollList()){
            if(!(poll.isPrivate()))
                an.add(new PollInfo(poll));
        }
        return an;
    }*/

    @PutMapping(value = "/user")
    public void updateUser(@RequestBody UserInfo userInfo, Principal principal){
        if(!Validator.stringEmptyOrNull(userInfo.getEmail()))
            userService.emailExist(userInfo.getEmail());
        if(!Validator.stringEmptyOrNull(userInfo.getUsername()))
            userService.usernameExist(userInfo.getUsername());
        User user=userService.getUserByUsername(principal.getName());
        user.fillProperty(userInfo);
        userService.updateUser(user);

    }
    @DeleteMapping(value = "/user")
    public void deleteUser(Principal principal){
        userService.deleteUser(userService.getUserByUsername(principal.getName()).getId());
    }
}
