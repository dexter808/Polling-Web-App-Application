package com.spring.polls.models.entities;

import javax.persistence.*;

@Entity(name = "Options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "poll_id",nullable = false)
    private Poll poll;
    @Column(name = "text",length = 50)
    private String text;
    @Column(name = "optionNumber",nullable = false)
    private Integer optionNumber;
    public Option(){
    }

    public Integer getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(Integer optionNumber) {
        this.optionNumber = optionNumber;
    }

    public Option(Poll poll, String text, Integer optionNumber) {
        this.poll = poll;
        this.text = text;
        this.optionNumber = optionNumber;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
