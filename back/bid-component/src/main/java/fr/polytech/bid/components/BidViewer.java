package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotClosedException;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;

import java.util.List;

public interface BidViewer {

    List<Bid> getBids();
    Bid getBidById(Long id) throws BidNotFoundException;
    List<Offer> getOffers(Long id) throws BidNotFoundException;
    Offer getAcceptedOffer(Long id) throws BidNotFoundException, BidNotClosedException;
    List<Bid> getBidsBySupplierId(long supplierId) throws SupplierNotFoundException;
}
