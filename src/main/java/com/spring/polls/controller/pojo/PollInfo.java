package com.spring.polls.controller.pojo;

import com.spring.polls.controller.helper.ResponseObject;
import com.spring.polls.models.entities.Option;
import com.spring.polls.models.entities.Poll;
import com.spring.polls.models.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PollInfo{
    private String title;
    private String description;
    private Long pollId;
    private boolean isPrivate;
    private ArrayList<String> authorizedUsername = new ArrayList<>();
    private ArrayList<String> authorizedEmail = new ArrayList<>();
    private HashMap<Integer, String> options = new HashMap<>();

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public ArrayList<String> getAuthorizedUsername() {
        return authorizedUsername;
    }

    public void setAuthorizedUsername(ArrayList<String> authorizedUsername) {
        this.authorizedUsername = authorizedUsername;
    }

    public ArrayList<String> getAuthorizedEmail() {
        return authorizedEmail;
    }

    public void setAuthorizedEmail(ArrayList<String> authorizedEmail) {
        this.authorizedEmail = authorizedEmail;
    }

    @Override
    public String toString() {
        return "PollInfo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pollId=" + pollId +
                ", options=" + options +
                '}';
    }

    public PollInfo(String title, String description, HashMap<Integer, String> options) {
        this.description = description;
        this.options = options;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PollInfo(Poll poll) {
        this.description = poll.getDesc();
        List<Option> options = poll.getOptions();
        for (Option option : options) {
            this.options.put(option.getOptionNumber(), option.getText());
        }
        this.title = poll.getTitle();
        this.pollId = poll.getId();
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
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
