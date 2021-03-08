package fr.polytech.maintenance.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.maintenance.models.Mishap;


public interface MishapRepository extends CrudRepository<Mishap, Long> {}