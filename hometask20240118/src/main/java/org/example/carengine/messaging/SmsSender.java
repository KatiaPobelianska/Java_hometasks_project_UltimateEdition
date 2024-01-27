package org.example.carengine.messaging;

import org.example.carengine.service.MessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsSender {
    private final MessageGenerator generator;
@Autowired
    public SmsSender(MessageGenerator generator) {
        this.generator = generator;
    }
    public void sendSms(){
        System.out.println(generator.getRandomMessage());
    }
}
