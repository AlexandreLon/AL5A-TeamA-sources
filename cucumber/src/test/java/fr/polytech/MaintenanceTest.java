package fr.polytech;

import fr.polytech.api.SupplierService;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.polytech.api.MaintenanceService;
import fr.polytech.models.Task;
import fr.polytech.models.TaskType;

@SpringBootTest
public class MaintenanceTest {

    private String maintenanceName;
    private List<Task> tasks;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private SupplierService supplierService;

    @ParameterType("VERIFICATION|REPLACING|CLEANING")
    public TaskType taskType(String priority) {
        switch (priority) {
            case "VERIFICATION":
                return TaskType.VERIFICATION;
            case "REPLACING":
                return TaskType.REPLACING;
            case "CLEANING":
                return TaskType.CLEANING;
            default:
                return null;
        }
    }

    @Given("A {string} to do")
    public void aMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    @When("I create a maintenance with type {taskType} for it")
    public void createMaintenance(TaskType maintenanceType) {
        maintenanceService.createMaintenance(maintenanceName, maintenanceType, new Date());
    }

    @And("I get all tasks with maintenance")
    public void getAllTasksWithMaintenance() {
        this.tasks = supplierService.getTasks();
    }

    @Then("I have a {string} maintenance in tasks")
    public void taskAdded(String taskName) {
        List<String> taskNameList = tasks.stream().map(element -> element.getName()).collect(Collectors.toList());
        assertTrue(taskNameList.contains(taskName));
    }

}
