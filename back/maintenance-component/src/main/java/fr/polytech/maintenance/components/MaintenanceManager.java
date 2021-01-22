package fr.polytech.maintenance.components;

import fr.polytech.maintenance.errors.MaintenanceNotFound;
import fr.polytech.maintenance.models.Maintenance;

import java.util.Date;
import java.util.List;

public interface MaintenanceManager {
    Maintenance createMaintenance(String name, String type, Date desiredDate);

    List<Maintenance> getMaintenances();

    Maintenance getMaintenanceById(Long id) throws MaintenanceNotFound;

    Maintenance updateMaintenance(Long id, String name, String type) throws MaintenanceNotFound;

    void deleteMaintenance(Long id);
}
