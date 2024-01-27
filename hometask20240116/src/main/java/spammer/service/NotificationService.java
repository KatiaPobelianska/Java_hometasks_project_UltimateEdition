package spammer.service;

import spammer.model.Customer;

/*1.2 Створіть клас NotificationService, який виконує оповіщення клієнтів за
допомогою методу sendSpam(Customer customer): якщо з останньої відправки пройшло більше 30 днів,
то він відправляє спам за наявними контактами (метод делегує цю роботу класам від яких залежить -
SmsNotificationService та EmailNotificationService). Отримайте NotificationService з контексту та передайте
 йому біни клієнтів. SmsNotificationService та EmailNotificationService
повинні впроваджуватися в NotificationService.*/
public interface NotificationService {
    void sendSpam(Customer customer);
}
