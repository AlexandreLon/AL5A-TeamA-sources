package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;

import java.util.List;

public interface BidViewer {

    List<Bid> getBids();
    Bid getBidById(Long id) throws BidNotFoundException;
	List<Offer> getOffers(Long id) throws BidNotFoundException;
}
