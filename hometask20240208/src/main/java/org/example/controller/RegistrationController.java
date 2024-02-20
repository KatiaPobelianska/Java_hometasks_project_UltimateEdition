package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.ParticipantFormData;
import org.example.pojo.Participant;
import org.example.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegistrationController {
    private RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("participant", new ParticipantFormData());
        return "participant_registration";
    }

    @PostMapping("/registration")
    public String createParticipant(@ModelAttribute(name = "participant") @Valid ParticipantFormData participantFormData,
                                    BindingResult result,
                                    Model model
    ) {
        if (result.hasErrors()) {
            return "participant_registration";
        }
        Participant participant = new Participant(participantFormData.getFirstName(),
                participantFormData.getLastName(),
                participantFormData.getAge(),
                participantFormData.getIsAgree());
        service.addParticipant(participant);
        model.addAttribute("name",participant.firstName());
        return "redirect:/participants";
    }
    @GetMapping("/participants")
    @ResponseBody
    public String getParticipants() {
        return service.getParticipants().toString();
    }

}
