package com.spring.polls.models.entities;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "UID")
    private String UID=null;
    @OneToOne
    @JoinColumn(name = "option_id",nullable = false)
    private Option option;

    @ManyToOne
    @JoinColumn(name = "poll_id",nullable = false)
    private Poll poll;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Vote(User user, Option option, Poll poll) {
        this.user = user;
        this.option = option;
        this.poll = poll;
    }

    public Vote() {
    }

}
