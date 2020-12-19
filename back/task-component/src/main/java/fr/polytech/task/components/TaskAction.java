package fr.polytech.task.components;

import fr.polytech.task.errors.TaskNotFound;
import fr.polytech.task.models.Task;

public interface TaskAction {

    Task endTask(Long id) throws TaskNotFound;
    
}
