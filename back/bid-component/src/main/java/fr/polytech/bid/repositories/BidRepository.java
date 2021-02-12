package fr.polytech.bid.repositories;

import fr.polytech.bid.models.Bid;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findByTaskType(TaskType supplierTaskType);
    Bid findByTaskId(Long id);
}