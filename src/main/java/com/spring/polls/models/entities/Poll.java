package com.spring.polls.models.entities;

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
    @Column(name = "text",length = 100)
    private String text;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
    private List<Option> options=new ArrayList<>();
    public Poll() {
    }

    @Override
    public String toString() {
        return "Poll{" +
                "Id=" + Id +
                ", user=" + user.getUsername() +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }

    public Poll(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
