package spring.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import spring.homework.model.Task;
import spring.homework.model.TaskList;

@Configuration
@ComponentScan("spring.homework")
@PropertySource("classpath:application.properties")

public class SpringConfig {
    @Value("${default.task.title}")
    private String defaultTaskTitle;

    @Value("${default.task.description}")
    private String defaultTaskDescription;

    @Value("${default.task.priority}")
    private int defaultTaskPriority;

    @Value("${default.task.list.size}")
    private int defaultTaskListSize;

    @Bean
    public Task defaultTask() {
        return new Task(defaultTaskTitle, defaultTaskDescription, defaultTaskPriority);
    }

    @Bean
    public TaskList defaultTaskList(Task defaultTask) {
        return new TaskList(defaultTaskListSize, defaultTask);
    }


}
