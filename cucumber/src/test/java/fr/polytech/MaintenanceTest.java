package fr.polytech;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.api.DepartmentManager;
import fr.polytech.api.MaintenanceManager;
import fr.polytech.models.Task;

public class MaintenanceTest {

    private String maintenanceName;
    private Task[] tasks;
    private MaintenanceManager maintenanceManager = new MaintenanceManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    @Given("A {string} to do")
    public void aMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    @When("I create a maintenance for it")
    public void createMaintenance() {
        maintenanceManager.createMaintenance(maintenanceName);
    }

    @And("I get all tasks with maintenance")
    public void getAllTasksWithMaintenance() {
        this.tasks = departmentManager.getTasks();
    }

    @Then("I have a {string} maintenance in tasks")
    public void taskAdded(String taskName) {
        List<String> taskNameList = Arrays.asList(tasks).stream().map(element -> element.getName()).collect(Collectors.toList());
        assertTrue(taskNameList.contains(taskName));
    }

}
