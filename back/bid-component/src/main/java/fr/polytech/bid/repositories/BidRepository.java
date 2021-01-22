package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Bid;
import org.springframework.data.repository.CrudRepository;


public interface BidRepository extends CrudRepository<Bid, Long> {}