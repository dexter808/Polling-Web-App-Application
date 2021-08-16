package com.spring.polls.models.entities;

import javax.persistence.*;

@Entity
public class PUStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "poll_id",nullable = false)
    private Long pollId;
    @Column(name = "username",nullable = false)
    private String username;

    public PUStorage(Long pollId, String username) {
        this.pollId = pollId;
        this.username = username;
    }

    public PUStorage() {
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
