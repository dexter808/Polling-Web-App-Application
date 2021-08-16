package com.spring.polls.models.entities;

import com.spring.polls.controller.pojo.PollInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String desc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
    private List<Option> options=new ArrayList<>();
    @Column(name = "is_private")
    private boolean isPrivate=false;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "poll")
    private List<Vote> votes=new ArrayList<>();

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Poll(User user, String title, String desc, boolean isPrivate) {
        this.user = user;
        this.title = title;
        this.desc = desc;
        this.isPrivate = isPrivate;
    }

    public Poll() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
