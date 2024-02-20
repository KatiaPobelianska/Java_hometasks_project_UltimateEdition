package org.example.service;

import org.example.pojo.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {
    private List<Participant> participants;

    public RegistrationService() {
        this.participants = new ArrayList<>();
    }

    public List<Participant> getParticipants() {
        return participants;
    }
    public void addParticipant(Participant participant){
        participants.add(participant);
    }
}
