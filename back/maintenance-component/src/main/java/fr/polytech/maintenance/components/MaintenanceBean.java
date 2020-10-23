package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import fr.polytech.task.components.TaskCreator;

@Component
@ComponentScan({ "fr.polytech.task" })
public class MaintenanceBean implements MaintenanceManager {

    @Autowired
    TaskCreator taskCreator;

    @Override
    public Maintenance createMaintenance(String name, String type) {
        Maintenance maintenance = new Maintenance(name, type);
        taskCreator.createTask(maintenance);
        return maintenance;
    }
}
