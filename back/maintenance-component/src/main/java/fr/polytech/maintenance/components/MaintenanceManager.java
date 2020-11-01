package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;

import java.util.List;

public interface MaintenanceManager {
    Maintenance createMaintenance(String name, String type);

    List<Maintenance> getMaintenances();

    Maintenance getMaintenanceById(Long id);

    Maintenance updateMaintenance(Long id, String name, String type);

    void deleteMaintenance(Long id);
}
