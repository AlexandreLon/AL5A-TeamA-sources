package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;

import java.util.List;

public interface MaintenanceManager {
    void scheduleMaintenance();
    Maintenance getMaintenance();
//    List<Maintenance> getMaintenances();


}
