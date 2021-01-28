package fr.polytech.bid.components;

import java.util.Date;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Offer;

public interface BidProposer {
    Offer outbid(long bidId, long supplierId, double price, Date proposedDate) throws BidNotFoundException, SupplierNotFoundException;
}
