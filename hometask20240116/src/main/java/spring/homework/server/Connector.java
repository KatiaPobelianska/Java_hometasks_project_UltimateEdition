package spring.homework.server;

import org.springframework.stereotype.Component;

@Component
public class Connector {
    public void connect(){
        System.out.println("Start connecting...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Connection successful.");
    }
}
