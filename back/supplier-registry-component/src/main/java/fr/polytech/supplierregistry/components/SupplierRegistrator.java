package fr.polytech.supplierregistry.components;

import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.TaskType;

public interface SupplierRegistrator {
    Supplier create(String name, TaskType taskType);
}
