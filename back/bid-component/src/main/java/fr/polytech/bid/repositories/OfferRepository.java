package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.OfferStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findByBidId(Long id);
    Offer findByBidIdAndStatus(Long id, OfferStatus status);
}