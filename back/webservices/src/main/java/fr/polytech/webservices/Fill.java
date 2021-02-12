package fr.polytech.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.TaskType;
import fr.polytech.task.repositories.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.BidStatus;
import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.OfferStatus;
import fr.polytech.bid.repositories.BidRepository;
import fr.polytech.bid.repositories.OfferRepository;


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
    private TaskRepository tr;

    @Autowired
    private MishapRepository mir;

    @Autowired
    private BidRepository br;

    @Autowired
    private SupplierRepository sr;

    @Autowired
    private OfferRepository or;

    private Map<TaskType, List<Supplier>> suppliers = new HashMap<>();

    private Faker faker = new Faker();

    TaskType[] types = { TaskType.CLEANING, TaskType.REPLACING, TaskType.VERIFICATION };

    public TaskPriority generateTaskPriority() {
        int v = faker.random().nextInt(3);
        switch (v) {
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

    public TaskStatus generateTaskStatus() {
        int v = faker.random().nextInt(2);
        switch (v) {
            case 0:
                return TaskStatus.WAITING_FOR_BID_CLOSURE;
            case 1:
                return TaskStatus.PENDING;
            case 2:
                return TaskStatus.FINISHED;
            default:
                throw new IllegalStateException("Invalid random " + v);
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
            m.setStatus(generateTaskStatus());
            m.setType(types[faker.random().nextInt(0, 2)]);
            if(faker.random().nextBoolean()){
                m.setRealizationDate(faker.date().future(5, TimeUnit.DAYS));
            }
            createBidFromTask(mar.save(m));
        }
    }

    private void generateSomeMishaps() {
        for(int i=0; i<20; i++) {
            Mishap m = new Mishap();
            m.setCreationDate(faker.date().past(3, TimeUnit.DAYS));
            m.setName(faker.lorem().word());
            m.setPriority(generateTaskPriority());
            m.setStatus(generateTaskStatus());
            m.setType(types[faker.random().nextInt(0, 2)]);
            if(faker.random().nextBoolean()){
                m.setRealizationDate(faker.date().future(5, TimeUnit.DAYS));
            }
            createBidFromTask(mir.save(m));
        }
    }

    private void generateSomeSuppliers() {
        for(int i=0; i<20; i++) {
            Supplier s = new Supplier();
            s.setName(faker.name().fullName());
            s.setTasks(new ArrayList<>());
            s.setTaskType(types[faker.random().nextInt(0,2)]);
            if(suppliers.get(s.getTaskType()) == null){
                suppliers.put(s.getTaskType(), new ArrayList<>());
            }
            suppliers.get(s.getTaskType()).add(sr.save(s));
        }
    }

    private Bid createBidFromTask(Task task) {
        Bid bid = new Bid();
        bid.setDesiredDate(faker.date().future(5, TimeUnit.DAYS));
        bid.setName(task.getName());
        bid.setTask(task);
        boolean isBidClosed = task.getRealizationDate() != null;
        bid.setStatus((isBidClosed ? BidStatus.CLOSED : BidStatus.ONGOING));
        bid = br.save(bid);
        generateSomeOfferFromBid(bid, isBidClosed);
        return br.save(bid);
    }

    private void generateSomeOfferFromBid(Bid bid, boolean isBidClosed) {
        List<Offer> offers = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Offer offer = new Offer();
            offer.setBid(bid);
            List<Supplier> typeAssignedSuppliers = suppliers.get(bid.getTask().getType());
            offer.setSupplier(typeAssignedSuppliers.get(faker.random().nextInt(0, typeAssignedSuppliers.size()-1)));
            offer.setProposedDate(faker.date().future(5, TimeUnit.DAYS));
            offer.setPrice(faker.random().nextDouble()*10000);
            offer.setStatus(OfferStatus.PENDING);
            offer = or.save(offer);
            offers.add(offer);
        }
        if(isBidClosed){
            List<Offer> savedOffers = or.findByBidId(bid.getId());
            acceptOffer(savedOffers, bid);
        }
    }

    private void acceptOffer(List<Offer> offers, Bid bid){
        Offer offerToAccept = offers.get(faker.random().nextInt(0, 9));
        offers.remove(offerToAccept);
        for (Offer off : offers) {
            off.setStatus(OfferStatus.REJECTED);
        }
        Task task = bid.getTask();
        task.setRealizationDate(offerToAccept.getProposedDate());
        task.setPrice(offerToAccept.getPrice());
        offerToAccept.getSupplier().getTasks().add(task);
        offerToAccept.setStatus(OfferStatus.ACCEPTED);
        tr.save(task);
        br.save(bid);
    }
}
