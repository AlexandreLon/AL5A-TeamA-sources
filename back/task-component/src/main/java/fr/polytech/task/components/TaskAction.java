package fr.polytech.task.components;

import fr.polytech.task.errors.TaskNotFoundException;
import fr.polytech.task.models.Task;

public interface TaskAction {

    Task endTask(Long id) throws TaskNotFoundException;
    
}
