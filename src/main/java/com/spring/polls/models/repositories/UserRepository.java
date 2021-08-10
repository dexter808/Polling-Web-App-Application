package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
