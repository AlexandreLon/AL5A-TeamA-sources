package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Offer;

import org.springframework.data.repository.CrudRepository;


public interface OfferRepository extends CrudRepository<Offer, Long> {}