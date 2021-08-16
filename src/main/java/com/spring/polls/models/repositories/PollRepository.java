package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long> {
    List<Poll> findAllByUser(User user);
}
