package fr.polytech.maintenance.components;

import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.maintenance.repositories.MaintenanceRepository;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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
        this.maintenanceRepository.save(maintenance);
        return maintenance;
    }

    @Override
    public List<Maintenance> getMaintenances() {
        return (List<Maintenance>) this.maintenanceRepository.findAll();
    }

    @Override
    public Maintenance getMaintenanceById(Long id) {
        Optional<Maintenance> opt = this.maintenanceRepository.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    public Maintenance updateMaintenance(Long id, String name, String type) {
        Optional<Maintenance> opt = this.maintenanceRepository.findById(id);
        if (opt.isPresent()) {
            Maintenance maintenance = opt.get();
            maintenance.setName(name);
            maintenance.setType(type);
            this.maintenanceRepository.save(maintenance);
            return maintenance;
        }
        return null;
    }

    @Override
    public void deleteMaintenance(Long id) {
        this.maintenanceRepository.deleteById(id);
    }
}
