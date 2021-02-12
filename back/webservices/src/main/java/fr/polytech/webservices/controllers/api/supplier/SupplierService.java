package fr.polytech.webservices.controllers.api.supplier;

import fr.polytech.bid.components.OfferManager;
import fr.polytech.bid.components.BidViewer;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Offer;

import fr.polytech.supplierregistry.components.SupplierProvider;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.Task;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.BadRequestException;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.webservices.models.OfferBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.lang.System.exit;

@RestController
@ComponentScan({"fr.polytech.bid", "fr.polytech.supplierregistry"})
@RequestMapping("/api/supplier")
public class SupplierService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private OfferManager offerManager;

    @Autowired
    private SupplierProvider supplierProvider;

    @Autowired
    private BidViewer bidViewer;

    @CrossOrigin
    @GetMapping("")
    public List<Supplier> getSuppliers() {
        log.info("GET : /api/supplier");
        return supplierProvider.getSuppliers();
    }

    @CrossOrigin
    @GetMapping("/{id}/tasks")
    public List<Task> getSupplierTasks(@PathVariable Long id) {
        log.info("GET : /api/supplier/" + id + "/tasks");
        try {
            return supplierProvider.getTasksBySupplierId(id);
        } catch (SupplierNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PostMapping("/{id}/outbid")
    public Offer outbid(@PathVariable Long id, @RequestBody OfferBody offerBody) {
        log.info("GET : /api/supplier/" + id + "/outbid");
        try {
            return offerManager.outbid(id, offerBody.supplierId, offerBody.price, offerBody.proposedDate);
        } catch (BidNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (SupplierNotFoundException e) {
            throw new BadRequestException();
        }
    }

    @CrossOrigin
    @GetMapping("/{id}/bids")
    public List<Bid> getBidsBySupplierId(@PathVariable long id) {
        log.info("GET : /api/supplier/" + id + "/bids");
        try {
            return bidViewer.getBidsBySupplierId(id);
        } catch (SupplierNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

}
