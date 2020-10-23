package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;

public interface MaintenanceManager {
    Maintenance createMaintenance(String name, String type);
}
