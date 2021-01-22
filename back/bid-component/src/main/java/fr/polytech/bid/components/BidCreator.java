package fr.polytech.bid.components;

import fr.polytech.task.models.Task;

import java.util.Date;

public interface BidCreator {

    void createBid(Task task, Date desiredDate);
}
