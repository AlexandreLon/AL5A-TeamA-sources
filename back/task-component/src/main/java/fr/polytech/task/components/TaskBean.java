package fr.polytech.task.components;

import org.springframework.stereotype.Component;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.models.Task;

@Component
public class TaskBean implements TaskCreator, TaskAction {

    private Task task;

    public void createTask(Task task){
        this.task = task;
    }

    public void endTask(){
        this.task.setStatus(TaskStatus.FINISHED);
    }

    public Task getTask(){
        return this.task;
    }
}