package fr.polytech.bid.components;

import fr.polytech.bid.models.Bid;
import fr.polytech.task.models.Task;

import java.util.Date;

public interface BidCreator {

    Bid createBid(Task task, Date desiredDate);
}
