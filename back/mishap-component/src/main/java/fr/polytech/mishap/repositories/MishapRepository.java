package fr.polytech.mishap.repositories;

import fr.polytech.task.models.TaskStatus;
import org.springframework.data.repository.CrudRepository;

import fr.polytech.mishap.models.Mishap;

import java.util.List;

public interface MishapRepository extends CrudRepository<Mishap, Long> {

    List<Mishap> findAllByStatus(TaskStatus status);
}