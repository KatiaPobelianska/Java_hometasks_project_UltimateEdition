package spammer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import spammer.model.Customer;
import spammer.service.NotificationService;
import spammer.service.impl.EmailNotificationService;
import spammer.service.impl.SmsNotificationService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//        Customer customer1 = context.getBean("customer1", Customer.class);
//        Customer customer2 = context.getBean("customer2", Customer.class);
//        System.out.println(customer1);
//        System.out.println(customer2);
//
//        NotificationService notificationService = context.getBean("notificationService", NotificationService.class);
////        System.out.println(notificationService.getCustomerList());

        EmailNotificationService emailNS = context.getBean("emailNotificationService", EmailNotificationService.class);
        emailNS.sendSpamAllCustomers();

        SmsNotificationService smsNS = context.getBean("smsNotificationService", SmsNotificationService.class);
        smsNS.sendSpamAllCustomers();

        context.close();
    }
}
