package com.spring.polls.controller;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.entities.Vote;
import com.spring.polls.models.repositories.*;
import com.spring.polls.service.UserService;
import com.spring.polls.service.interfaces.PollsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.UID;
import java.security.Principal;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollsService pollsService;

    @Autowired
    private UserService userService;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private PEStorageRepository peStorageRepository;

    @Autowired
    private PUStorageRepository puStorageRepository;
    @GetMapping("/vote/{pollId}/option/{optionNumber}")
    public ResponseEntity<ResponseObject<String>> voteByLoggedUser(@PathVariable Long pollId, @PathVariable Integer optionNumber, Principal principal){
        Poll poll=pollsService.getPollById(pollId);
        Option option=poll.getOptions().get(optionNumber-1);
        User user=userService.getUserByUsername(principal.getName());
        Vote vote=new Vote(user,option,poll);
        if(poll.isPrivate()){
            if(!(puStorageRepository.existsPUStorageByPollIdAndUsername(poll.getId(),principal.getName()))){
                return new ResponseEntity<>(
                        new ResponseObject<String>(
                                "Not Authorized",
                                "DENIED"
                                ),
                        HttpStatus.UNAUTHORIZED
                );
            }else if(voteRepository.existsVoteByUserAndPoll(user,poll))
                return new ResponseEntity<>(
                        new ResponseObject<String>(
                                "Only one Time Vote allowed",
                                "DENIED"
                                ),
                        HttpStatus.BAD_REQUEST
                );
        }else{
            if(voteRepository.existsVoteByUserAndPoll(user,poll))
                return new ResponseEntity<>(
                        new ResponseObject<String>(
                                "Only one Time Vote allowed",
                                "DENIED"
                                ),
                        HttpStatus.BAD_REQUEST
                );
        }
        voteRepository.save(vote);
        return new ResponseEntity<>(
                new ResponseObject<String>(
                        "Vote casted",
                        "DONE"
                ),
                HttpStatus.OK
        );
    }
    /*
    @GetMapping("/vote/{pollId}/{UID}/{optionNumber}")
    public ResponseEntity<String> voteByEmail(@PathVariable Long pollId,@PathVariable String UID, @PathVariable Integer optionNumber, Principal principal){
        if(!(peStorageRepository.existsPEStorageByPollIdAndUID(pollId, UID)))
            return new ResponseEntity<>("Only authorized users can vote", HttpStatus.UNAUTHORIZED);
        if(voteRepository.existsVoteByUIDAndPoll(UID,pollRepository.getById(pollId)))
            return new ResponseEntity<>("Only one time vote allowed", HttpStatus.BAD_REQUEST);
        Poll poll=pollRepository.getById(pollId);
        Option option=poll.getOptions().get(optionNumber);
        Vote vote=new Vote(null,option,poll);
        vote.setUID(UID);
        voteRepository.save(vote);
        return new ResponseEntity<String>("Vote has been casted",HttpStatus.OK);
    }
     */
}
