package fr.polytech.task.repositories;

import fr.polytech.task.models.TaskPriority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.polytech.task.models.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findAllByOrderByPriorityDesc();
}