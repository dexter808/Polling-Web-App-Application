package com.spring.polls.controller.pojo;

import com.spring.polls.models.entities.Option;

public class OptionInfo {
    Integer votes=0;
    String optionsText="";

    public OptionInfo(Option option){
        votes=option.getVotes().size();
        optionsText=option.getText();
    }
    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getOptionsText() {
        return optionsText;
    }

    public void setOptionsText(String optionsText) {
        this.optionsText = optionsText;
    }

    public OptionInfo() {
    }

    public OptionInfo(Integer optionNumber, Integer votes, String optionsText) {
        this.votes = votes;
        this.optionsText = optionsText;
    }
}
