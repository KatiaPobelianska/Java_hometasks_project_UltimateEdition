package org.example.controllers;
/*1 Создайте простое веб-приложение, которое принимает POST-запрос с текстом и
добавляет этот текст в список. В ответ на POST запрос приложение отправляет представление с
текстом «Ваше сообщение принято».
По GET-запросу приложение возвращает список всех сообщений в виде JSON.*/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
 private MessageService messageService;

 @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(){
     ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(messageService), HttpStatus.OK);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/messages")
    public ResponseEntity<?> postMessage(@RequestParam(name = "message") String message) {
     messageService.putMessage(message);
     return ResponseEntity.ok("Your message is accepted.");
    }
}
