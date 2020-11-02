package fr.polytech.mishap.components;

import java.util.List;

import fr.polytech.mishap.models.Mishap;
import fr.polytech.task.models.TaskPriority;

public interface MishapManager {
    Mishap createMishap(String name, String type, TaskPriority priority);

    List<Mishap> getMishaps();

    Mishap getMishapById(Long id);

    Mishap updateMishap(Long id, String name, String type, TaskPriority priority);

    void deleteMishap(Long id);
}
