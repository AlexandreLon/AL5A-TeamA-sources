package fr.polytech.maintenance.components;

import fr.polytech.maintenance.errors.MaintenanceNotFoundException;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.task.models.TaskType;

import java.util.Date;
import java.util.List;

public interface MaintenanceManager {
    Maintenance createMaintenance(String name, TaskType type, Date desiredDate);

    List<Maintenance> getMaintenances();

    Maintenance getMaintenanceById(Long id) throws MaintenanceNotFoundException;

    Maintenance updateMaintenance(Long id, String name, TaskType type) throws MaintenanceNotFoundException;

    void deleteMaintenance(Long id);
}
