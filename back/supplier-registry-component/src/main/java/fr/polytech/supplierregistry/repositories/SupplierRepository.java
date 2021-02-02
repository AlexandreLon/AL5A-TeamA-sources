package fr.polytech.supplierregistry.repositories;

import fr.polytech.supplierregistry.models.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
