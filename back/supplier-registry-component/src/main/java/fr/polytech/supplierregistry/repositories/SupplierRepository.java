package fr.polytech.supplierregistry.repositories;

import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.TaskType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    List<Supplier> findSupplierByTaskType(TaskType taskType);
}
