package fr.polytech.maintenance.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.task.models.Task;

public interface MaintenanceRepository extends CrudRepository<Task, Long> {}