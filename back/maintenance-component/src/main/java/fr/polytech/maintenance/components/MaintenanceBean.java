package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.maintenance.repositories.MaintenanceRepository;

import org.springframework.stereotype.Component;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.polytech.task.models.TaskStatus;

@Component
@ComponentScan("fr.polytech.maintenance.repositories")
@EntityScan("fr.polytech.maintenance.models")
@EnableJpaRepositories("fr.polytech.maintenance.repositories")
public class MaintenanceBean implements MaintenanceManager {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance createMaintenance(String name, String type) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(name);
        maintenance.setType(type);
        maintenance.setStatus(TaskStatus.PENDING);
        maintenance.setCreationDate(new Date());
        maintenanceRepository.save(maintenance);
        return maintenance;
    }
}
