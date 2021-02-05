package fr.polytech;

import fr.polytech.api.MaintenanceService;
import fr.polytech.api.SupplierService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class OutibidTest {

    @Autowired
    private SupplierService supplierService;

    @Given("a supplier named {string}")
    public void aSupplierNamed(String arg0) {
    }

    @And("a maintenance named {string}")
    public void aMaintenanceNamed(String arg0) {
    }

    @And("an existing bid associated with the maintenance")
    public void anExistingBidAssociatedWithTheMaintenance() {
    }

    @When("{string} makes an offer of {int} dollars on the maintenance {string} with the date {string}")
    public void makesAnOfferOfDollarsOnTheMaintenanceWithTheDate(String arg0, int arg1, String arg2, String arg3) {
    }

    @Then("a new offer is created")
    public void aNewOfferIsCreated() {
    }

    @And("the offer is linked with {string}")
    public void theOfferIsLinkedWith(String arg0) {
    }

    @And("the offer is linked with the bid {string}")
    public void theOfferIsLinkedWithTheBid(String arg0) {
    }
}
