package spring.homework.services.impl;

import org.springframework.stereotype.Component;
import spring.homework.services.NotificationService;
@Component("email")
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendMessage() {
        System.out.println("Sent by email.");
    }
}
