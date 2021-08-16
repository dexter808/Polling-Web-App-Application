package com.spring.polls.service;

import com.spring.polls.controller.pojo.PollInfo;
import com.spring.polls.models.entities.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailSenderService {
    @Value("spring.mail.email")
    String defaultEmail;
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendCustomEmail(String toEmail,
                                String body,
                                String subject){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(defaultEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        System.out.println("Mail Sent");
    }
    public void sendInviteMail(String toEmail,
                               String username,
                               PollInfo pollInfo,
                               String UID){
        String option="";
        for (Map.Entry<Integer, String> opt : pollInfo.getOptions().entrySet()) {
            option+=opt.getKey()+" "+opt.getValue()+"\n"+"localhost:8080/vote/"+pollInfo.getPollId()+"/"+UID+"/"
                    +opt.getKey()+"\n\n";
        }
        String body=String.format(
                "Hi,\n%s has invited you to vote for this private poll." +
                        "\nTo vote just click on the link to the option.\n" +
                        "%s\n\n%s\n\n%s\n\nThank you.",
                username,
                pollInfo.getTitle(),
                pollInfo.getDescription(),
                option
        );
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(defaultEmail);
        message.setTo(toEmail);
        message.setSubject("Invite for poll");
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("Mail Sent");
    }
}
