package fr.polytech.al.trainmanagement.maintenance.components;

import fr.polytech.al.trainmanagement.maintenance.models.Maintenance;

import java.util.List;

public interface MaintenanceManager {
    void scheduleMaintenance();
    Maintenance getMaintenance(int idMaintenance);
//    List<Maintenance> getMaintenances();


}
