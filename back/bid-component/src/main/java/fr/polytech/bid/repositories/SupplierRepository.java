package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
