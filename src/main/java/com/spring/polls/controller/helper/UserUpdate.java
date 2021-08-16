package com.spring.polls.controller.helper;

import com.spring.polls.controller.pojo.UserInfo;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserUpdate {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //Info-Update
    public void infoUpdate(UserInfo userInfo,User user){
        UserInfo userInfo1=new UserInfo(user);
        userInfo1.copyInfo(userInfo);
        userRepository.updateUserInfoById(userInfo1.getFirstname()
                ,userInfo1.getLastName()
                , userInfo1.getPhoneNumber()
                , userInfo1.getEmail()
                ,userInfo1.getUsername(),
                user.getId());
    }

    //Configuration Update
    /*public void configUpdate(User newConfigUser,User user){
        userRepository.updateUserConfigById(newConfigUser.isAccountNonLocked(),
                newConfigUser.isCredentialsNonExpired(),
                newConfigUser.isEnabled(),
                newConfigUser.isAccountNonExpired(),
                user.getId());
    }*/
}
