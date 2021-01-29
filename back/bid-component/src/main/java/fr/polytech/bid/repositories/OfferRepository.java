package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Offer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findByBidId(Long id);

}