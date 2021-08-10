package com.spring.polls.models.entities;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "option_id",nullable = false)
    private Option option;

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

    public Vote() {
    }

    public Vote(User user, Option option) {
        this.user = user;
        this.option = option;
    }
}
