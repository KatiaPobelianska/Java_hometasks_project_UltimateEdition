package spring.homework.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TaskList {
    private List<Task> tasks;

    public TaskList(int size, Task defaultTask) {
        tasks = new ArrayList<>(Collections.nCopies(size, defaultTask));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
