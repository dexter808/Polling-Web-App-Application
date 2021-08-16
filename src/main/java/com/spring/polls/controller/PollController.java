package com.spring.polls.controller;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.controller.pojo.PollResult;
import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.PEStorage;
import com.spring.polls.models.entities.PUStorage;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.repositories.*;
import com.spring.polls.service.EmailSenderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class PollController {
    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private PEStorageRepository peStorageRepository;

    @Autowired
    private PUStorageRepository puStorageRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    static Pattern pattern=Pattern.compile("^(.+)@(.+)$");
    public static boolean checkValid(String s){
        return pattern.matcher(s).matches();
    }
    @PostMapping("/poll")
    public PollInfo pollCreate(@RequestBody PollInfo pollInfo, Principal principal){
        Poll poll=new Poll(userRepository.findByUsername(principal.getName()),pollInfo.getTitle(),pollInfo.getDescription(),pollInfo.isPrivate());
        pollRepository.save(poll);
        pollInfo.getOptions().forEach((K,V)->optionRepository.save(new Option(poll,V,K)));
        pollInfo.setPollId(poll.getId());
        if(pollInfo.isPrivate()){
            System.out.println("Private poll");
            for(String email:pollInfo.getAuthorizedEmail()){
                System.out.println("Got "+email+"---------");
                if(PollController.checkValid(email))
                {
                    String UID= pollInfo.getPollId()+RandomStringUtils.random(10,true,true);
                    peStorageRepository.save(new PEStorage(pollInfo.getPollId(),email,UID));
                    emailSenderService.sendInviteMail(email,principal.getName(),pollInfo,UID);
                }
            }
            for(String username:pollInfo.getAuthorizedUsername()){
                System.out.println("Got "+username+"---------");
                if(userRepository.existsUserByUsername(username))
                    puStorageRepository.save(new PUStorage(pollInfo.getPollId(),username));
            }
        }
        return pollInfo;
    }

    @GetMapping("/poll/private")
    public ArrayList<PollInfo> readPoll(Principal principal){
        ArrayList<PollInfo> an=new ArrayList<>();
        for(Poll poll:userRepository.findByUsername(principal.getName()).getPollList()){
            if((poll.isPrivate()))
                an.add(new PollInfo(poll));
        }
        return an;
    }
    @GetMapping("/poll/{pollId}")
    public ResponseEntity<ResponseObject> readSpecificPoll(@PathVariable Long pollId){
        PollInfo pollInfo=new PollInfo(pollRepository.getById(pollId));
        if(pollInfo.isPrivate())
            return new ResponseEntity<>(new ResponseObject("Not Allowed"),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(pollInfo,HttpStatus.OK);
    }
    @GetMapping("/poll/private/{pollId}")
    public ResponseEntity<ResponseObject> readSpecificPoll(Principal principal,@PathVariable Long pollId){
        PollInfo pollInfo=new PollInfo(pollRepository.getById(pollId));
        if(!(puStorageRepository.existsPUStorageByPollIdAndUsername(pollId,principal.getName())))
            return new ResponseEntity<>(new ResponseObject("Not Allowed"),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(pollInfo,HttpStatus.OK);
    }
    @GetMapping("/poll/result/{pollId}")
    public ResponseEntity<ResponseObject> getResult(@PathVariable Long pollId,Principal principal){
        Poll poll=pollRepository.getById(pollId);
        if(poll.isPrivate()){
            if(puStorageRepository.existsPUStorageByPollIdAndUsername(pollId,principal.getName()) || poll.getUser().getUsername().equals(principal.getName()))
                return new ResponseEntity<>(new PollResult(poll),HttpStatus.OK);
            else
                return new ResponseEntity<>(new ResponseObject("Not authorized"),HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>(new PollResult(poll),HttpStatus.OK);
        }
    }
    @DeleteMapping("/poll/{pollId}")
    public ResponseEntity<ResponseObject> deletePoll(@PathVariable Long pollId, Principal principal){
        Poll poll=pollRepository.getById(pollId);
        if(!(poll.getUser().getUsername().equals(principal.getName())))
        return new ResponseEntity<>(new ResponseObject("Not Authorized"), HttpStatus.UNAUTHORIZED);
        pollRepository.delete(poll);
        return new ResponseEntity<>(new ResponseObject("Success"),HttpStatus.OK);
    }
}
