package fr.polytech;

import fr.polytech.api.BidManagerService;
import fr.polytech.api.BidService;
import fr.polytech.api.MishapManager;
import fr.polytech.api.SupplierService;
import fr.polytech.models.MishapPriority;
import fr.polytech.models.Supplier;
import fr.polytech.models.Task;
import fr.polytech.models.bid.Bid;
import fr.polytech.models.bid.Offer;
import fr.polytech.models.mishap.Mishap;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class AcceptOfferBid {

    @Autowired
    private MishapManager mishapManager;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private BidService bidService;

    @Autowired
    private BidManagerService bidManagerService;

    private Bid bid;
    private Supplier supplier;
    private Offer offer;

    @Given("Patrick create a mishap of type {string} and priority {mishapPriority}")
    public void createMishap(String mishapType, MishapPriority mishapPriority) {
        String mishapName = "Mishap Breakdown";
        Mishap mishap = mishapManager.createMishap(mishapName, mishapType, mishapPriority);
        Optional<Bid> optional = bidService.getBids().stream().filter(e -> e.getTask().getId().equals(mishap.getId())).findFirst();
        assertTrue(optional.isPresent());
        bid = optional.get();
    }

    @And("John as supplier")
    public void supplier() {
        supplier = supplierService.getSuppliers().get(0);
    }

    @And("John outbid {int} today")
    public void outbid(int value) {
        offer = new Offer();
        offer.setPrice(value);
        offer.setProposedDate(new Date());
        offer.setSupplierId(supplier.getId());
        offer = supplierService.outbid(bid.getId(), offer);
    }

    @When("Patrick accept John's offer")
    public void accept() {
        bidManagerService.accept(offer.getId());
    }

    @Then("Offer is accepted")
    public void isAccepted() {
        assertTrue(supplierService.getTasks().stream().anyMatch(e -> e.getId().equals(bid.getTask().getId())));
    }
}