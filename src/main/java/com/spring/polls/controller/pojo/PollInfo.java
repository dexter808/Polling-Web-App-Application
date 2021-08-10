package com.spring.polls.controller.pojo;

import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;

import java.util.HashMap;
import java.util.List;

public class PollInfo {
    private String description;
    private HashMap<Integer,String> options=new HashMap<>();

    public PollInfo(String description, HashMap<Integer, String> options) {
        this.description = description;
        this.options = options;
    }

    public PollInfo(Poll poll){
        this.description=poll.getText();
        List<Option> options=poll.getOptions();
        for(Option option:options){
            this.options.put(option.getOptionNumber(),option.getText());
        }
    }

    public HashMap<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Integer, String> options) {
        this.options = options;
    }

    public PollInfo() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
