package com.spring.polls.controller;

import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.repositories.OptionRepository;
import com.spring.polls.models.repositories.PollRepository;
import com.spring.polls.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PollController {
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OptionRepository optionRepository;
    @PostMapping(value = "/poll/create")
    public PollInfo createPoll(@RequestBody PollInfo pollInfo, Principal principal){
        Poll poll1 = new Poll(userRepository.findByUsername(principal.getName()), pollInfo.getDescription());
        pollRepository.save(poll1);
        pollInfo.getOptions().forEach((k, v) -> optionRepository.save(new Option(poll1,v,k)));
        return pollInfo;
    }
    @GetMapping(value = "/poll/{username}")
    public ArrayList<PollInfo> getPollInfo (@PathVariable String username){
        User user=userRepository.findByUsername(username);
        ArrayList<PollInfo> pollInfoArrayList=new ArrayList<>();
        List<Poll> polls=pollRepository.findAllByUser(user);
        for(Poll poll:polls){
            pollInfoArrayList.add(new PollInfo(poll));
        }
        return pollInfoArrayList;
    }
}
