package fr.polytech.bid.components;

import java.util.Date;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.bid.models.Offer;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;

public interface OfferManager {
    Offer acceptOffer(Long id) throws OfferNotFoundException;
    
    Offer outbid(long bidId, long supplierId, double price, Date proposedDate)
            throws BidNotFoundException, SupplierNotFoundException;
}
