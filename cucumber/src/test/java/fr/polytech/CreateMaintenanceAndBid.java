package fr.polytech;

import fr.polytech.api.BidService;
import fr.polytech.api.MaintenanceService;
import fr.polytech.models.TaskType;
import fr.polytech.models.bid.Bid;
import fr.polytech.models.maintenance.Maintenance;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
public class CreateMaintenanceAndBid {

    @Autowired
    private MaintenanceService manu;

    @Autowired
    private BidService bidService;

    Maintenance maintenance;
    Bid bid;


    @When("Manu creates a new maintenance {string} for {string}")
    public void manuCreatesANewMaintenance(String name, String dateText) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(dateText);
        this.maintenance = manu.createMaintenance(name, TaskType.VERIFICATION, date);
    }

    @Then("a new maintenance is created with the name {string}")
    public void aNewMaintenanceIsCreated(String maintenanceName) {
        List<Maintenance> maintenances = manu.getMaintenances();

        Optional<Maintenance> optMaintenance = maintenances.stream().filter(maintenance ->
                this.maintenance.getId().equals(maintenance.getId())
                        && this.maintenance.getName().equals(maintenance.getName())).findFirst();
        assertTrue(optMaintenance.isPresent());
        this.maintenance = optMaintenance.get();
    }

    @And("its realizationDate is not defined")
    public void itsRealizationDateIsNotDefined() {
        assertNull(this.maintenance.getRealizationDate());
    }

    @And("a new bid {string} is created and visible by Superman")
    public void aNewBidIsCreatedAndVisibleBySuperman(String maintenanceName) {
        List<Bid> bids = bidService.getBids();
        this.bid = bids.stream().filter(bid ->
                bid.getName().equals(maintenanceName)).collect(Collectors.toList()).get(0);
        assertEquals(maintenanceName, this.bid.getName());
    }

    @And("the desired date associated is {string}")
    public void theDesiredDateAssociatedIsTZ(String dateText) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        assertEquals(dateText, formatter.format(this.bid.getDesiredDate()));
    }

}
