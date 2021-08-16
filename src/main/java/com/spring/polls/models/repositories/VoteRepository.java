package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import com.spring.polls.models.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    boolean existsVoteByUIDAndPoll(String UID, Poll poll);
    boolean existsVoteByUserAndPoll(User user,Poll poll);
}
