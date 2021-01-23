package fr.polytech.maintenance.components;

import com.google.inject.internal.util.Lists;
import fr.polytech.bid.components.BidCreator;
import fr.polytech.bid.repositories.SupplierRepository;
import fr.polytech.maintenance.errors.MaintenanceNotFoundException;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.maintenance.repositories.MaintenanceRepository;

import fr.polytech.task.models.TaskPriority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import fr.polytech.task.models.TaskStatus;

@Component
@ComponentScan({"fr.polytech.maintenance.repositories", "fr.polytech.bid.components"})
@EntityScan("fr.polytech.maintenance.models")
@EnableJpaRepositories("fr.polytech.maintenance.repositories")
public class MaintenanceBean implements MaintenanceManager {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BidCreator bidCreator;

    @Override
    public Maintenance createMaintenance(String name, String type, Date desiredDate) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(name);
        maintenance.setType(type);
        maintenance.setStatus(TaskStatus.PENDING);
        maintenance.setCreationDate(new Date());
        maintenance.setPriority(TaskPriority.NONE);
        maintenance = maintenanceRepository.save(maintenance);
        bidCreator.createBid(maintenance, Lists.newArrayList(supplierRepository.findAll()), desiredDate);
        return maintenance;
    }

    @Override
    public List<Maintenance> getMaintenances() {
        return (List<Maintenance>) this.maintenanceRepository.findAll();
    }

    @Override
    public Maintenance getMaintenanceById(Long id) throws MaintenanceNotFoundException {
        Optional<Maintenance> opt = this.maintenanceRepository.findById(id);
        if(!opt.isPresent()) {
            throw new MaintenanceNotFoundException();
        }
        return opt.get();
    }

    @Override
    public Maintenance updateMaintenance(Long id, String name, String type) throws MaintenanceNotFoundException {
        Optional<Maintenance> opt = this.maintenanceRepository.findById(id);
        if (opt.isPresent()) {
            Maintenance maintenance = opt.get();
            maintenance.setName(name);
            maintenance.setType(type);
            maintenance = this.maintenanceRepository.save(maintenance);
            return maintenance;
        }
        throw new MaintenanceNotFoundException();
    }

    @Override
    public void deleteMaintenance(Long id) {
        this.maintenanceRepository.deleteById(id);
    }
}
