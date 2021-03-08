package fr.polytech.supplierregistry.components;

import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskType;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan("fr.polytech.supplierregistry.repositories")
@EntityScan("fr.polytech.supplierregistry.models")
@EnableJpaRepositories("fr.polytech.supplierregistry.repositories")
public class SupplierRegisterBean implements SupplierProvider, SupplierAssignator, SupplierRegistrator {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getSuppliers() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) throws SupplierNotFoundException {
        Optional<Supplier> opt = supplierRepository.findById(id);
        if (!opt.isPresent())
            throw new SupplierNotFoundException();
        return opt.get();
    }

    @Override
    public List<Supplier> getSuppliers(TaskType taskType) {
        return supplierRepository.findSupplierByTaskType(taskType);
    }

    @Override
    public List<Task> getTasksBySupplierId(Long id) throws SupplierNotFoundException {
        Optional<Supplier> opt = supplierRepository.findById(id);
        if (!opt.isPresent())
            throw new SupplierNotFoundException();
        Supplier supplier = opt.get();
        return supplier.getTasks();
    }

    @Override
    public TaskType getSupplierTaskTypeById(long id) throws SupplierNotFoundException {
        Optional<Supplier> opt = supplierRepository.findById(id);
        if (!opt.isPresent()) {
            throw new SupplierNotFoundException();
        }
        return opt.get().getTaskType();
    }

    public Supplier create(String name, TaskType taskType) {
        Supplier supplier = new Supplier();
        supplier.setTasks(new ArrayList<>());
        supplier.setName(name);
        supplier.setTaskType(taskType);
        return supplierRepository.save(supplier);
    }
}
