package com.spring.polls.controller.pojo;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.models.entities.Poll;

import java.util.HashMap;
import java.util.Map;

public class PollResult extends ResponseObject{
    String title;
    String description;
    HashMap<Integer,OptionInfo> options=new HashMap<>();
    OptionInfo MostVotedOption;

    public PollResult(Poll poll){
        title=poll.getTitle();
        description=poll.getDesc();
        poll.getOptions().forEach(option -> options.put(option.getOptionNumber(),new OptionInfo(option)));
        OptionInfo optionInfo1=new OptionInfo();
        for(Map.Entry<Integer,OptionInfo> opt:options.entrySet())
            if(opt.getValue().getVotes()>optionInfo1.getVotes())
                optionInfo1=opt.getValue();
        MostVotedOption=optionInfo1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Integer, OptionInfo> getOptions() {
        return options;
    }

    public void setOptions(HashMap<Integer, OptionInfo> options) {
        this.options = options;
    }

    public OptionInfo getMostVotedOption() {
        return MostVotedOption;
    }

    public void setMostVotedOption(OptionInfo mostVotedOption) {
        MostVotedOption = mostVotedOption;
    }

    public PollResult(String title, String description, HashMap<Integer, OptionInfo> options, OptionInfo mostVotedOption) {
        this.title = title;
        this.description = description;
        this.options = options;
        MostVotedOption = mostVotedOption;
    }
}
