package fr.polytech.bid.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.bid.models.Supplier;
import fr.polytech.task.models.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.repositories.TaskRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan({"fr.polytech.task.repositories", "fr.polytech.bid.components"})
@EntityScan("fr.polytech.task.models")
@EnableJpaRepositories("fr.polytech.task.repositories")
public class BidTest {

    @Autowired
    private BidCreator bidCreator;

    @Autowired
    private BidViewer bidViewer;

    @Autowired 
    private TaskRepository tr;

    @Test
    public void createBid() {
        Task task = new Task();
        task.setCreationDate(new Date());
        task.setDesiredDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.PENDING);
        task.setType("bar");
        task = tr.save(task);

        Supplier s = new Supplier();
        s.setName("mecalex");
        s.setTaskType(TaskType.VERIFICATION);

        Bid bid = bidCreator.createBid(task,List.of(s), new Date());
        List<Bid> bids = bidViewer.getBids().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, bids.size());
        Bid gettingBid = bids.get(0);
        assertEquals(bid, gettingBid);
    }

    @Test
    public void getBidById() {
        Task task = new Task();
        task.setCreationDate(new Date());
        task.setDesiredDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.PENDING);
        task.setType("bar");
        task = tr.save(task);

        Supplier s = new Supplier();
        s.setName("mecalex");
        s.setTaskType(TaskType.VERIFICATION);

        Bid bid = bidCreator.createBid(task,List.of(s), new Date());
        List<Bid> bids = bidViewer.getBids().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, bids.size());
        Bid gettingBid = bids.get(0);
        assertEquals(bid, gettingBid);
    }
    
    @Test
    public void getMishapByIdExist() {
        Task task = new Task();
        task.setCreationDate(new Date());
        task.setDesiredDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.PENDING);
        task.setType("bar");
        task = tr.save(task);

        Supplier s = new Supplier();
        s.setName("mecalex");
        s.setTaskType(TaskType.VERIFICATION);

        Bid bid = bidCreator.createBid(task,List.of(s), new Date());
        assertDoesNotThrow(() -> {
            Bid gettingBid = bidViewer.getBidById(bid.getId());
            assertEquals(bid, gettingBid);
		});
    }

    @Test
    public void getMishapByIdDoesntExist() {
        assertThrows(BidNotFoundException.class, () -> {
			bidViewer.getBidById(100000l);
		});
    }
}
