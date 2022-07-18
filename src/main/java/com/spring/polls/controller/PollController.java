package com.spring.polls.controller;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.controller.pojo.PollResult;
import com.spring.polls.exception.user.NoSuchUserExistsException;
import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.PEStorage;
import com.spring.polls.models.entities.PUStorage;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.repositories.*;
import com.spring.polls.service.EmailSenderService;
import com.spring.polls.service.UserService;
import com.spring.polls.service.interfaces.PollsService;
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
    private PollsService pollsService;
    @Autowired
    private UserService userService;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private PUStorageRepository puStorageRepository;

    @PostMapping("/poll")
    public ResponseEntity<ResponseObject<PollInfo>> pollCreate(@RequestBody PollInfo pollInfo, Principal principal){
        Poll poll=new Poll(userService.getUserByUsername(principal.getName()),pollInfo.getTitle(),pollInfo.getDescription(),pollInfo.isPrivate());
        pollsService.createPoll(poll);
        pollInfo.getOptions().forEach((K,V)->optionRepository.save(new Option(poll,V,K)));
        pollInfo.setPollId(poll.getId());
        if(pollInfo.isPrivate()){
            //System.out.println("Private poll");
            /* Email Invite, Closing this feature for now
            for(String email:pollInfo.getAuthorizedEmail()){
                System.out.println("Got "+email+"---------");
                if(PollController.checkValid(email))
                {
                    String UID= pollInfo.getPollId()+RandomStringUtils.random(10,true,true);
                    peStorageRepository.save(new PEStorage(pollInfo.getPollId(),email,UID));
                    emailSenderService.sendInviteMail(email,principal.getName(),pollInfo,UID);
                }
            }*/
            for(String username:pollInfo.getAuthorizedUsername()){
                userService.doeesNotExistsUsername(username);
                puStorageRepository.save(new PUStorage(pollInfo.getPollId(),username));
            }
        }
        return new ResponseEntity<>(
                new ResponseObject<>("Poll Created",
                        pollInfo),
                HttpStatus.OK
        );
    }

    @GetMapping("/poll/private")
    public ResponseEntity<ResponseObject<ArrayList<PollInfo>>> readPoll(Principal principal){
        ArrayList<PollInfo> an=new ArrayList<>();
        for(Poll poll:userService.getUserByUsername(principal.getName()).getPollList()){
            if((poll.isPrivate()))
                an.add(new PollInfo(poll));
        }
        return new ResponseEntity<>(
                new ResponseObject<ArrayList<PollInfo>>(
                        "Polls Found",
                        an
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/poll/{pollId}")
    public ResponseEntity<ResponseObject<PollInfo>> readSpecificPoll(@PathVariable Long pollId){
        PollInfo pollInfo=new PollInfo(pollsService.getPollById(pollId));
        if(pollInfo.isPrivate())
            return new ResponseEntity<>(new ResponseObject<PollInfo>("Not Allowed",null),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(
                new ResponseObject<>(
                        "Poll Found",
                        pollInfo
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/poll/private/{pollId}")
    public ResponseEntity<ResponseObject<PollInfo>> readSpecificPoll(Principal principal,@PathVariable Long pollId){
        PollInfo pollInfo=new PollInfo(pollsService.getPollById(pollId));
        if(!pollInfo.isPrivate())
            return new ResponseEntity<>(
                    new ResponseObject<PollInfo>(
                            "Used only for private polls",
                            null
                    ),
                    HttpStatus.BAD_REQUEST
            );
        if(!(puStorageRepository.existsPUStorageByPollIdAndUsername(pollId,principal.getName())))
            return new ResponseEntity<>(
                    new ResponseObject<PollInfo>(
                            "Not Authorized",
                            null
                    ),
                    HttpStatus.UNAUTHORIZED
            );
        return new ResponseEntity<>(
                new ResponseObject<PollInfo>(
                        "OK",
                        pollInfo
                ),
                HttpStatus.OK
        );
    }
    @GetMapping("/poll/result/{pollId}")
    public ResponseEntity<ResponseObject<PollResult>> getResult(@PathVariable Long pollId,Principal principal){
        Poll poll=pollsService.getPollById(pollId);
        if(poll.isPrivate()){
            if(puStorageRepository.existsPUStorageByPollIdAndUsername(pollId,principal.getName()) || poll.getUser().getUsername().equals(principal.getName()))
                return new ResponseEntity<>(
                        new ResponseObject<PollResult>(
                                "Poll Result Found",
                                new PollResult(poll)
                        ),
                        HttpStatus.OK
                );
            else
                return new ResponseEntity<>(
                        new ResponseObject<PollResult>(
                                "Not authorized",
                                null
                        ),
                        HttpStatus.UNAUTHORIZED
                );
        }else{
            return new ResponseEntity<>(
                    new ResponseObject<PollResult>(
                            "Poll Result Found",
                            new PollResult(poll)
                    ),
                    HttpStatus.OK
            );
        }
    }
    @DeleteMapping("/poll/{pollId}")
    public ResponseEntity<ResponseObject<String>> deletePoll(@PathVariable Long pollId, Principal principal){
        Poll poll=pollsService.getPollById(pollId);
        if(!(poll.getUser().getUsername().equals(principal.getName())))
        return new ResponseEntity<>(new ResponseObject<String>(
                "Not Authorized",
                "DENIED"
                ),
                HttpStatus.UNAUTHORIZED
        );
        pollsService.delete(poll);
        for(Option option:poll.getOptions())
            if(optionRepository.existsById(option.getId()))
            optionRepository.delete(option);
        return new ResponseEntity<>(
                new ResponseObject<String>(
                        "Success",
                        "DONE"
                ),
                HttpStatus.OK
        );
    }
}
