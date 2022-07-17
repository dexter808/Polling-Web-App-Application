package com.spring.polls.models.repositories;

import com.spring.polls.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET first_name=:fn,last_name=:ln,phn=:phnNumber,email=:email," +
            "username=:username " +
            "where id=:userId",nativeQuery = true)
    void updateUserInfoById(@Param("fn") String fn,
                                   @Param("ln") String ln,
                                   @Param("phnNumber") String phn,
                                   @Param("email") String email,
                                   @Param("username") String username,
                                   @Param("userId") Long userId);

}
