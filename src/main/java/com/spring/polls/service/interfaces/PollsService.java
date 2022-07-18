package com.spring.polls.service.interfaces;

import com.spring.polls.models.entities.Poll;

public interface PollsService {
    Poll getPollById(Long id);
    void createPoll(Poll poll);

}
