package fr.polytech.webservices.controllers.api.supplier;

import fr.polytech.bid.components.BidProposer;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Offer;

import fr.polytech.supplierregistry.components.SupplierAuthenticator;
import fr.polytech.supplierregistry.models.Supplier;
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

@RestController
@ComponentScan({ "fr.polytech.bid","fr.polytech.supplierregistry" })
@RequestMapping("/api/supplier")
public class SupplierService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BidProposer bidProposer;

    @Autowired
    private SupplierAuthenticator supplierAuthenticator;

    @CrossOrigin
    @GetMapping("")
    public List<Supplier> getSuppliers() {
        log.info("GET : /api/supplier");
        return supplierAuthenticator.getSuppliers();

    }

    @CrossOrigin
    @PostMapping("/{id}/outbid")
    public Offer outbid(@PathVariable Long id, @RequestBody OfferBody offerBody) {
        log.info("GET : /api/supplier/" + id + "/outbid");
        try {
            return bidProposer.outbid(id, offerBody.supplierId, offerBody.price, offerBody.proposedDate);
        } catch (BidNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (SupplierNotFoundException e) {
            throw new BadRequestException();
        }
    }

}
