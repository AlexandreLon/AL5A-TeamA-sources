package fr.polytech.supplierregistry.components;


import fr.polytech.supplierregistry.models.Supplier;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierRegistryTest {


    @Test
    public void createSupplierRegistry() {
        Supplier supplier = new Supplier();
        /*task.setCreationDate(new Date());
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
        assertEquals(bid, gettingBid);*/
    }

    @Test
    public void getSupplierById() {
        /*Task task = new Task();
        task.setCreationDate(new Date());
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
        assertEquals(bid, gettingBid);*/
    }
}
