package fr.polytech.task.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.models.Task;

@Component("taskBean")
public class TaskBean implements TaskCreator, TaskAction, ScheduleVisualizer{

    private List<Task> tasks = new ArrayList<>();

    public void createTask(Task task){
        this.tasks.add(task);
    }

    public Task endTask(Long id){
        Task current = null;
        for(Task task : tasks) {
            if(task.getId() == id) {
                task.setStatus(TaskStatus.FINISHED);
                current = task;
            }
        }
        return current;
    }
    
	@Override
	public List<Task> getPlanning() {
        return tasks;
	}
}