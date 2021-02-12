package fr.polytech.mishap.components;

import java.util.Date;
import java.util.List;

import fr.polytech.mishap.errors.MishapNotFoundException;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskType;

public interface MishapManager {
    Mishap createMishap(String name, TaskType type, Date desiredDate, TaskPriority priority);

    List<Mishap> getMishaps();

    Mishap getMishapById(Long id) throws MishapNotFoundException;

    Mishap updateMishap(Long id, String name, TaskType type, TaskPriority priority) throws MishapNotFoundException;

    void abortMishap(Long id) throws MishapNotFoundException;
}
