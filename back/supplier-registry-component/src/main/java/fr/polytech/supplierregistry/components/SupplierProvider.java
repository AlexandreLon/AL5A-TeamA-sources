package fr.polytech.supplierregistry.components;

import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskType;

import java.util.List;

public interface SupplierProvider {
    Supplier getSupplierById(Long id) throws SupplierNotFoundException;
    List<Supplier> getSuppliers();
    TaskType getSupplierTaskTypeById(long id) throws SupplierNotFoundException;
    List<Task> getTasksBySupplierId(Long id) throws SupplierNotFoundException;
}
