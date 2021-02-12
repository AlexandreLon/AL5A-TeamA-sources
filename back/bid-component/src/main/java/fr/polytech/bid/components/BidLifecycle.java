package fr.polytech.bid.components;

import fr.polytech.bid.models.Bid;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.Task;

import java.util.Date;
import java.util.List;

public interface BidLifecycle {

    Bid createBid(Task task, List<Supplier> suppliers, Date desiredDate);
    void abortBidFromTask(Task task);
}
