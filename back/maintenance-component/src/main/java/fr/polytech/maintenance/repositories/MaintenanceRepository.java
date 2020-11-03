package fr.polytech.maintenance.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.maintenance.models.Maintenance;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {}