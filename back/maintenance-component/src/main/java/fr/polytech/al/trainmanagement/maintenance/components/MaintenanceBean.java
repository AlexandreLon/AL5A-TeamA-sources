package fr.polytech.al.trainmanagement.maintenance.components;

import fr.polytech.al.trainmanagement.maintenance.models.Maintenance;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceBean implements MaintenanceManager {

    private Maintenance maintenance;

    @Override
    public void scheduleMaintenance() {
        this.maintenance = new Maintenance("New maintenance", "type");
    }

    @Override
    public Maintenance getMaintenance(int idMaintenance) {
        return this.maintenance;
    }
}
