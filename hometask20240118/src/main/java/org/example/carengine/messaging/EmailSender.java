package org.example.carengine.messaging;

import org.example.carengine.service.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final MessageGenerator generator;
@Autowired
    public EmailSender(MessageGenerator generator) {
        this.generator = generator;
    }
    public void sendMessage(){
        System.out.println(generator.getRandomMessage());
    }
}
