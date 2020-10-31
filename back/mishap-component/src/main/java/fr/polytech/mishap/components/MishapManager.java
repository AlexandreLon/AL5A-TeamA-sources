package fr.polytech.mishap.components;

import java.util.List;

import fr.polytech.mishap.models.Mishap;
import fr.polytech.mishap.models.MishapPriority;
import fr.polytech.task.models.TaskStatus;

public interface MishapManager {
    Mishap createMishap(String name, String type, MishapPriority priority);

    List<Mishap> getMishaps();

    Mishap getMishapById(Long id);

    Mishap updateMishap(Long id, String name, String type, MishapPriority priority);

    void deleteMishap(Long id);
}
