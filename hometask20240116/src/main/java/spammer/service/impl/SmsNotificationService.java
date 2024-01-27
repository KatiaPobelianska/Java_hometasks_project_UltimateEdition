package spammer.service.impl;

import spammer.model.Customer;
import spammer.service.NotificationService;

import java.time.LocalDate;
import java.util.List;

public class SmsNotificationService implements NotificationService {
    private List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public void sendSpam(Customer customer) {
        if (customer.getDateOfLastNotification().isBefore(LocalDate.now().minusDays(30))) {
            System.out.println("Sending sms to " + customer.getName());
        }
    }
    public void sendSpamAllCustomers(){
        for(Customer customer: customerList){
            sendSpam(customer);
        }
    }
}
