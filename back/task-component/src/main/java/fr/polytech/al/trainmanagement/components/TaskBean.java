package al.trainmanagement.task.components;

import org.springframework.stereotype.Component;
import al.trainmanagement.task.models.TaskStatus;

@Component
public class TaskBean implements TaskCreator, TaskAction {

    Task task;

    void createTask(Task task){
        this.task = task;
    }

    void endTask(){
        this.task.setStatus(TaskStatus.FINISHED);
    }

    Task getTask(){
        return this.task;
    }
}