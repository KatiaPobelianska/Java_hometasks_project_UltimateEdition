package spring.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.homework.config.SpringConfig;
import spring.homework.model.TaskList;
import spring.homework.server.Connector;
import spring.homework.services.NotificationService;

/*Решите задания, используя Spring и изученные аннотации.
 1.1 Напишите простое приложение для управления задачами (To-Do List). Создайте бины
 Задач, Список задач и т.д. Размер списка задач, их приоритеты, заголовок и описание по
умолчанию должны быть взяты из файла настроек.
 1.2 Дополните приложение. Приложение должно отправлять уведомления о срочных
задачах. Создайте интерфейс NotificationService, который будет иметь несколько
реализаций для отправки уведомлений по электронной почте, SMS и push. Используйте
аннотации @Component, @Qualifier, @Primary и другие для настройки внедрения
зависимостей.
 1.3 Приложение должно отправлять данные на сервер. Создайте бин класса Connector,
который эмулирует подключение к серверу. Подключение занимает длительное время,
поэтому не должно замедлять запуск программы.*/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(SpringConfig.class);
        Connector connector = context.getBean("connector",Connector.class);
        connector.connect();
        NotificationService service = context.getBean("sms", NotificationService.class);
        service.sendMessage();
        TaskList list = context.getBean("defaultTaskList",TaskList.class);
        System.out.println(list.getTasks());



        context.close();
    }

}
