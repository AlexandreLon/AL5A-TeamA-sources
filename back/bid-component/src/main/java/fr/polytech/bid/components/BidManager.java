package fr.polytech.bid.components;

import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.bid.models.Offer;

public interface BidManager {
    Offer acceptOffer(Long id) throws OfferNotFoundException;
}
