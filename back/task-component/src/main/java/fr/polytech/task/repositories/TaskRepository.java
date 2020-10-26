package fr.polytech.task.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.task.models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}