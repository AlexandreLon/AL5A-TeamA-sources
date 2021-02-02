package fr.polytech.task.components;

import java.util.List;

import fr.polytech.task.errors.TaskNotFoundException;
import fr.polytech.task.models.Task;

public interface TaskManager {

    Task endTask(Long id) throws TaskNotFoundException;

    List<Task> getTasks();
    
}
