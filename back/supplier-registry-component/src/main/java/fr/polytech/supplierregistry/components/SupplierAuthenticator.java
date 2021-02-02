package fr.polytech.supplierregistry.components;



import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;

import java.util.List;

public interface SupplierAuthenticator {
    Supplier getSupplierById(Long id) throws SupplierNotFoundException;
    List<Supplier> getSuppliers();


}
