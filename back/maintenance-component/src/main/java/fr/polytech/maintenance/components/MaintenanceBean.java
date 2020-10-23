package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import fr.polytech.task.components.TaskCreator;

@Component
@ComponentScan({ "fr.polytech.task" })
public class MaintenanceBean implements MaintenanceManager {

    private Maintenance maintenance;

    @Autowired
    TaskCreator taskCreator;

    @Override
    public void scheduleMaintenance() {
        this.maintenance = new Maintenance("New maintenance", "type");
        taskCreator.createTask(this.maintenance);
    }

    @Override
    public Maintenance getMaintenance() {
        return this.maintenance;
    }
}
