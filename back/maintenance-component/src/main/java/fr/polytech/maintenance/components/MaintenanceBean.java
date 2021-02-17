package fr.polytech.maintenance.components;

import com.google.inject.internal.util.Lists;
import fr.polytech.bid.components.BidLifecycle;
import fr.polytech.maintenance.errors.MaintenanceNotFoundException;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.maintenance.repositories.MaintenanceRepository;
import fr.polytech.maintenance.errors.MishapNotFoundException;
import fr.polytech.maintenance.models.Mishap;
import fr.polytech.maintenance.repositories.MishapRepository;
import fr.polytech.supplierregistry.components.SupplierAssignator;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskType;
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
@ComponentScan({"fr.polytech.maintenance.repositories", "fr.polytech.bid.components","fr.polytech.supplierregistry.repositories"})
@EntityScan("fr.polytech.maintenance.models")
@EnableJpaRepositories("fr.polytech.maintenance.repositories")
public class MaintenanceBean implements MaintenanceManager, MishapManager {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private MishapRepository mishapRepository;

    @Autowired
    private SupplierAssignator supplierAssignator;

    @Autowired
    private BidLifecycle bidLifecycle;

    @Override
    public Maintenance createMaintenance(String name, TaskType type, Date desiredDate) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(name);
        maintenance.setType(type);
        maintenance.setStatus(TaskStatus.WAITING_FOR_BID_CLOSURE);
        maintenance.setCreationDate(new Date());
        maintenance.setPriority(TaskPriority.NONE);
        maintenance = maintenanceRepository.save(maintenance);
        try{
            bidLifecycle.createBid(maintenance, Lists.newArrayList(supplierAssignator.getSuppliers(type)), desiredDate);
        }
        catch (IllegalArgumentException e){ //If type enum doesn't fit
            throw new IllegalArgumentException("Maintenance type hasn't been recognized");
        }
        return maintenance;
    }

    @Override
    public Mishap createMishap(String name, TaskType type, Date desiredDate, TaskPriority priority) {
        Mishap mishap = new Mishap();
        mishap.setName(name);
        mishap.setType(type);
        mishap.setPriority(priority);
        mishap.setStatus(TaskStatus.WAITING_FOR_BID_CLOSURE);
        mishap.setCreationDate(new Date());
        mishap = mishapRepository.save(mishap);
        try{
            bidLifecycle.createBid(mishap, Lists.newArrayList(supplierAssignator.getSuppliers(type)), desiredDate);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Mishap type hasn't been recognized");

        }
        return mishap;
    }

    @Override
    public List<Maintenance> getMaintenances() {
        return (List<Maintenance>) this.maintenanceRepository.findAll();
    }

    @Override
    public List<Mishap> getMishaps() {
        return (List<Mishap>) this.mishapRepository.findAll();
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
    public Mishap getMishapById(Long id) throws MishapNotFoundException {
        Optional<Mishap> mishap = this.mishapRepository.findById(id);
        if(!mishap.isPresent()) throw new MishapNotFoundException();
        return mishap.get();
    }

    @Override
    public Maintenance updateMaintenance(Long id, String name, TaskType type) throws MaintenanceNotFoundException {
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
    public Mishap updateMishap(Long id, String name, 
            TaskType type, TaskPriority priority) throws MishapNotFoundException {
        Optional<Mishap> opt = this.mishapRepository.findById(id);
        if (opt.isPresent()) {
            Mishap mishap = opt.get();
            mishap.setName(name);
            mishap.setType(type);
            mishap.setPriority(priority);
            mishap = this.mishapRepository.save(mishap);
            return mishap;
        }
        throw new MishapNotFoundException();
    }

    @Override
    public void abortMaintenance(Long id) throws MaintenanceNotFoundException {
        Optional<Maintenance> opt = maintenanceRepository.findById(id);
        if (!opt.isPresent()) {
            throw new MaintenanceNotFoundException();
        }
        Maintenance maintenance = opt.get();
        maintenance.setStatus(TaskStatus.ABORTED);
        this.maintenanceRepository.save(maintenance);
        bidLifecycle.abortBidFromTask(maintenance);
    }

    @Override
    public void abortMishap(Long id) throws MishapNotFoundException {
        Optional<Mishap> opt = mishapRepository.findById(id);
        if (!opt.isPresent()) {
            throw new MishapNotFoundException();
        }
        Mishap mishap = opt.get();
        mishap.setStatus(TaskStatus.ABORTED);
        this.mishapRepository.save(mishap);
        bidLifecycle.abortBidFromTask(mishap);
    }
}
