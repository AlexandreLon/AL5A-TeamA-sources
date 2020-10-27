package fr.polytech.api;

import fr.polytech.models.Task;
import fr.polytech.models.TaskStatus;

public class DepartmentManager {

    Task[] tasks = new Task[2];

    public DepartmentManager() {
        tasks[0] = new Task();
        tasks[0].setName("rail replacement");
        tasks[0].setStatus(TaskStatus.PENDING);
        tasks[1] = new Task();
        tasks[1].setName("wagon breakdown");
        tasks[1].setStatus(TaskStatus.PENDING);
    }

	public Task[] getTasks() {
		return tasks;
	}

	public void done(Long id) {
        tasks[1].setStatus(TaskStatus.FINISHED);
	}
    
}
