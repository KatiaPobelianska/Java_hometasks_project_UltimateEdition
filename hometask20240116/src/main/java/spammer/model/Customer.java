package spammer.model;

import java.time.LocalDate;

/*1.1 Створіть клас Customer з полями id, name, dateOfLastNotification, phone, email,
 геттрами та сеттерами. У програмі створіть два біни типу Customer (використовуйте scope Prototype).
 Отримайте біни з контексту та встановіть першому параметри {1L, “Bob”, LocalDate.now(), “+19138445656”,
  null}, другому {2L, “Sarah”, 2024-16-01, “+19158455617”, ”sarah -sweet@candy.com”}.*/
public class Customer {
    private int id;
    private String name;
    private LocalDate dateOfLastNotification;
    private String phone;
    private String email;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfLastNotification() {
        return dateOfLastNotification;
    }

    public void setDateOfLastNotification(LocalDate dateOfLastNotification) {
        this.dateOfLastNotification = dateOfLastNotification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfLastNotification='" + dateOfLastNotification + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
