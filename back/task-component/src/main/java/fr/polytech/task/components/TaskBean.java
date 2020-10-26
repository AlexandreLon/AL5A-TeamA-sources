package fr.polytech.task.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.models.Task;

@Component("taskBean")
public class TaskBean implements TaskCreator, TaskAction, ScheduleVisualizer{

    private Task task;

    public void createTask(Task task){
        this.task = task;
    }

    public Task endTask(){
        this.task.setStatus(TaskStatus.FINISHED);
        return this.task;
    }
    
	@Override
	public List<Task> getPlanning() {
        return Arrays.asList(this.task,this.task);
	}
}