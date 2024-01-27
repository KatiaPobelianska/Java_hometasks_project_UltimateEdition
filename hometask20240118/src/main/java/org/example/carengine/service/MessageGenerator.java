package org.example.carengine.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("generator")
@Scope("prototype")
public class MessageGenerator {
    public String getRandomMessage(){
        int n = (int) (Math.random() * 80 + 21);
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int randomNumber = (int) (Math.random() * 4);
            switch (randomNumber) {
                case 0:
                    int random = (int) (Math.random() * 10);
                    builder.append(random);
                    break;
                case 1:
                    char random1 = (char) ((int) (Math.random() * 26) + 65);
                    builder.append(random1);
                    break;
                case 2:
                    char random2 = (char) ((int) (Math.random() * 26) + 97);
                    builder.append(random2);
                    break;
                case 3:
                    char[] specialChars = {'!', '_', '#', '%'};
                    char randomOfSpecialChars = specialChars[(int) (Math.random() * specialChars.length)];
                    builder.append(randomOfSpecialChars);
                    break;
            }
        }
        return builder.toString();
    }
}