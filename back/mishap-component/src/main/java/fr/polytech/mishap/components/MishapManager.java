package fr.polytech.mishap.components;

import java.util.Date;
import java.util.List;

import fr.polytech.mishap.errors.MishapNotFound;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.task.models.TaskPriority;

public interface MishapManager {
    Mishap createMishap(String name, String type, Date desiredDate, TaskPriority priority);

    List<Mishap> getMishaps();

    Mishap getMishapById(Long id) throws MishapNotFound;

    Mishap updateMishap(Long id, String name, String type, TaskPriority priority) throws MishapNotFound;

    void deleteMishap(Long id);
}
