package org.example;

import org.example.carengine.messaging.EmailSender;
import org.example.carengine.messaging.SmsSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*Ваш клас Car залежить від інтерфейсу Engine. Створіть кілька реалізацій
інтерфейсу Engine (наприклад, GasEngine та ElectricEngine). Анотуйте їх за допомогою
@Component з назвою бінів, щоб явно вказати, який двигун має бути
використаний у кожному випадку. Потім впровадьте залежність двигуна до класу Car з
допомогою @Autowired та @Qualifier, щоб можна було вибрати тип двигуна під час
компіляції.
 2 Створіть клас MessageGenerator, який генерує унікальні повідомлення з
тимчасовою міткою. Щоразу, коли бін запитується з контексту, створюється новий
екземпляр MessageGenerator. Впровадьте цей бін у класи EmailSender та SmsSender.*/
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Car car = context.getBean("car", Car.class);
//        System.out.println(car);

        EmailSender emailSender = context.getBean("emailSender", EmailSender.class);
        emailSender.sendMessage();

        SmsSender smsSender = context.getBean("sms", SmsSender.class);
        smsSender.sendSms();
        context.close();
    }
}
