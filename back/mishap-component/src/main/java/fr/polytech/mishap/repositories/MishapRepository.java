package fr.polytech.mishap.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.mishap.models.Mishap;

public interface MishapRepository extends CrudRepository<Mishap, Long> {}