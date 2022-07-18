package com.spring.polls.service;

import com.spring.polls.exception.poll.NoSuchPollException;
import com.spring.polls.exception.poll.PollAlreadyExistException;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.repositories.PollRepository;
import com.spring.polls.service.interfaces.PollsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollsService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Poll getPollById(Long id) {
        if(!pollRepository.existsById(id))
            throw new NoSuchPollException("Poll with this poll_id does not exist");
        return pollRepository.getById(id);
    }

    @Override
    public void createPoll(Poll poll) {
        if(!pollRepository.existsById(poll.getId()))
            throw new PollAlreadyExistException("This poll_id has been taken");
        pollRepository.save(poll);
    }
}
