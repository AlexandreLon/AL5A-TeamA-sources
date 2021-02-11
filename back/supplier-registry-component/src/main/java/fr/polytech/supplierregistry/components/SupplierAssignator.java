package fr.polytech.supplierregistry.components;


import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.TaskType;

import java.util.List;

public interface SupplierAssignator {
    List<Supplier> getSuppliers(TaskType taskType);

}
