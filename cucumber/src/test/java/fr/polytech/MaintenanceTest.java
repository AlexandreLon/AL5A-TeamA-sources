package fr.polytech;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.polytech.api.DepartmentManager;
import fr.polytech.api.MaintenanceManager;
import fr.polytech.models.Task;

@SpringBootTest
public class MaintenanceTest {

    private String maintenanceName;
    private List<Task> tasks;

    @Autowired
    private MaintenanceManager maintenanceManager;

    @Autowired
    private DepartmentManager departmentManager;

    @Given("A {string} to do")
    public void aMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    @When("I create a maintenance with type {string} for it")
    public void createMaintenance(String maintenanceType) {
        maintenanceManager.createMaintenance(maintenanceName, maintenanceType);
    }

    @And("I get all tasks with maintenance")
    public void getAllTasksWithMaintenance() {
        this.tasks = departmentManager.getTasks();
    }

    @Then("I have a {string} maintenance in tasks")
    public void taskAdded(String taskName) {
        List<String> taskNameList = tasks.stream().map(element -> element.getName()).collect(Collectors.toList());
        assertTrue(taskNameList.contains(taskName));
    }

}
