package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;

import java.util.List;

public interface BidViewer {

    List<Bid> getBids();
    Bid getBidById(Long id) throws BidNotFoundException;
}
