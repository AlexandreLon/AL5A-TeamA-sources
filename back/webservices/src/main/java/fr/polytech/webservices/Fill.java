package fr.polytech.webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import fr.polytech.task.models.TaskType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.BidStatus;
import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.Supplier;

import fr.polytech.bid.repositories.BidRepository;
import fr.polytech.bid.repositories.OfferRepository;
import fr.polytech.bid.repositories.SupplierRepository;


import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.maintenance.repositories.MaintenanceRepository;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.mishap.repositories.MishapRepository;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;

@Service
public class Fill {

    @Value("${env.mode}")
    private String env;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private MaintenanceRepository mar;

    @Autowired
    private MishapRepository mir;

    @Autowired
    private BidRepository br;

    @Autowired
    private SupplierRepository sr;

    @Autowired
    private OfferRepository of;

    private List<Supplier> suppliers = new ArrayList<>();

    private Faker faker = new Faker();

    public TaskPriority generateTaskPriority() {
        int v = faker.random().nextInt(3);
        switch(v) {
            case 0:
                return TaskPriority.HIGH;
            case 1:
                return TaskPriority.LOW;
            case 2:
                return TaskPriority.MEDIUM;
            default:
                return TaskPriority.NONE;
        }
    }

	public void generate() {
        log.info("Generating some data...");
        if (env.equals("dev")) {
            log.info("Generating some supplier");
            generateSomeSuppliers();
            log.info("Generating some maintenances with bid");
            generateSomeMaintenances();
            log.info("Generating some mishaps with bid");
            generateSomeMishaps();
        }
        log.info("Generating data done... Server up !!");
	}

    private void generateSomeMaintenances() {
        for(int i=0; i<20; i++) {
            Maintenance m = new Maintenance();
            m.setCreationDate(faker.date().past(3, TimeUnit.DAYS));
            m.setName(faker.lorem().word());
            m.setPriority(TaskPriority.NONE);
            m.setStatus(faker.random().nextBoolean() ? TaskStatus.PENDING : TaskStatus.FINISHED);
            m.setType(faker.lorem().word());
            m.setRealizationDate(faker.date().future(5, TimeUnit.DAYS));
            createBidFromTask(mar.save(m));
        }
    }

    private void generateSomeMishaps() {
        for(int i=0; i<20; i++) {
            Mishap m = new Mishap();
            m.setCreationDate(faker.date().past(3, TimeUnit.DAYS));
            m.setName(faker.lorem().word());
            m.setPriority(generateTaskPriority());
            m.setStatus(faker.random().nextBoolean() ? TaskStatus.PENDING : TaskStatus.FINISHED);
            m.setType(faker.lorem().word());
            m.setRealizationDate(faker.date().future(5, TimeUnit.DAYS));
            createBidFromTask(mir.save(m));
        }
    }

    private void generateSomeSuppliers() {
        TaskType[] types = {TaskType.CLEANING, TaskType.REPLACING, TaskType.VERIFICATION};
        for(int i=0; i<20; i++) {
            Supplier s = new Supplier();
            s.setName(faker.lorem().word());
            s.setTaskType(types[faker.random().nextInt(0,2)]);
            suppliers.add(s);
            sr.save(s);
        }
    }

    private Bid createBidFromTask(Task task) {
        Bid bid = new Bid();
        bid.setDesiredDate(faker.date().future(5, TimeUnit.DAYS));
        bid.setName(task.getName());
        bid.setTask(task);
        bid.setStatus((task.getRealizationDate() == null ? BidStatus.ONGOING : BidStatus.CLOSED));
        bid = br.save(bid);
        generateSomeOfferFromBid(bid, suppliers.get(faker.random().nextInt(0, suppliers.size()-1))); 
        return br.save(bid);
    }

    private void generateSomeOfferFromBid(Bid bid, Supplier supplier) {
        for(int i=0; i<10; i++) {
            Offer offer = new Offer();
            offer.setBid(bid);
            offer.setSupplier(supplier);
            offer.setProposedDate(faker.date().future(5, TimeUnit.DAYS));
            offer.setPrice(faker.random().nextDouble()*10000);
            of.save(offer);
        }
    }    
}
