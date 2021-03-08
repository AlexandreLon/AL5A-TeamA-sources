package fr.polytech.bid.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.notification.components.NotificationProducer;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.TaskType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.repositories.TaskRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan({"fr.polytech.task.repositories","fr.polytech.supplierregistry.repositories", "fr.polytech.bid.components","fr.polytech.notification"})
@EntityScan("fr.polytech.task.models")
@EnableJpaRepositories("fr.polytech.task.repositories")
@ExtendWith(MockitoExtension.class)
public class BidTest {

    @Autowired
    private BidLifecycle bidLifecycle;

    @Autowired
    private BidViewer bidViewer;

    @Autowired
    private OfferManager offerManager;

    @Autowired 
    private TaskRepository tr;

    @Autowired
    private SupplierRepository sr;

    @MockBean
    private NotificationProducer notificationProducer;

    private Task task;
    private Supplier supplier;
    private Bid bid;

    @BeforeEach
    public void initEach() {
        this.task = new Task();
        task.setCreationDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.WAITING_FOR_BID_CLOSURE);
        task.setType(TaskType.REPLACING);
        task = tr.save(task);

        this.supplier = new Supplier();
        supplier.setName("mecalex");
        supplier.setTaskType(TaskType.VERIFICATION);
        sr.save(supplier);

        this.bid = bidLifecycle.createBid(task, List.of(supplier), new Date());
    }

    @Test
    public void createBid() {
        List<Bid> bids = bidViewer.getBids().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, bids.size());
        Bid gettingBid = bids.get(0);
        assertEquals(bid, gettingBid);
    }

    @Test
    public void getBidById() {
        List<Bid> bids = bidViewer.getBids().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, bids.size());
        Bid gettingBid = bids.get(0);
        assertEquals(bid, gettingBid);
    }
    
    @Test
    public void getMishapByIdExist() {
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

    @Test
    public void outBidTestNotBid() {
        assertThrows(BidNotFoundException.class, () -> {
			offerManager.outbid(10000l, 0l, 10d, new Date());
		});
    }

    @Test
    public void outBidTestNotSupplier() {
        assertThrows(SupplierNotFoundException.class, () -> {
			offerManager.outbid(bid.getId(), 100000l, 10d, new Date());
		});
    }

    @Test
    public void outBidTest() {
        assertDoesNotThrow(() -> {
			offerManager.outbid(bid.getId(), this.supplier.getId(), 10d, new Date());
		});
    }

    @Test
    public void acceptOffer() {
        assertDoesNotThrow(() -> {
            Date proposedDate = new Date();
            Offer offer = offerManager.outbid(bid.getId(), this.supplier.getId(), 10d, proposedDate);
            offerManager.acceptOffer(offer.getId());
        });
    }

    @Test
    public void acceptOfferDoesntExist() {
        assertThrows(OfferNotFoundException.class, () -> {
            offerManager.acceptOffer(10000l);
        });
    }
}
