package fr.polytech.bid.components;

import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Supplier;
import fr.polytech.task.models.Task;

import java.util.Date;
import java.util.List;

public interface BidCreator {

    Bid createBid(Task task, List<Supplier> suppliers, Date desiredDate);
}
