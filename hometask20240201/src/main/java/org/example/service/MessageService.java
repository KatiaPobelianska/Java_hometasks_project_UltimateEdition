package org.example.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private List<String> messagesStrings;

    public MessageService() {
        messagesStrings = new ArrayList<>();
    }

    public List<String> getMessagesStrings() {
        return new ArrayList<>(messagesStrings);
    }
    public void putMessage(String message){
        this.messagesStrings.add(message);
    }
}
