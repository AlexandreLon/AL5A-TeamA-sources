package fr.polytech;

import fr.polytech.api.Api;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import fr.polytech.api.DepartmentManager;
import fr.polytech.api.MaintenanceManager;
import fr.polytech.models.Task;

//@ContextConfiguration(classes = SpringBootCucumberTest.class, loader = SpringBootContextLoader.class)
@SpringBootTest
public class MaintenanceTest {

    private String maintenanceName;
    private List<Task> tasks;

    @Autowired
    Api api;
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
