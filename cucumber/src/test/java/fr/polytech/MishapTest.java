package fr.polytech;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.api.DepartmentManager;
import fr.polytech.api.MishapManager;
import fr.polytech.models.Task;
import fr.polytech.models.TaskStatus;

public class MishapTest {

    private String mishapName;
    private List<Task> tasks;
    private MishapManager mishapManager = new MishapManager();
    private DepartmentManager departmentManager = new DepartmentManager();

    @Given("A {string} mishap")
    public void aMishapName(String mishapName) {
        this.mishapName = mishapName;
    }

    @When("I create a mishap with type {string} for it")
    public void createMishap(String mishapType) {
        mishapManager.createMishap(mishapName, mishapType);
    }

    @And("I get all tasks with mishap")
    public void getAllTasksWithMishap() {
        this.tasks = departmentManager.getTasks();
    }

    @Then("I have a {string} mishap in tasks")
    public void taskAdded(String taskName) {
        List<String> taskNameList = this.tasks.stream().map(element -> element.getName()).collect(Collectors.toList());
        assertTrue(taskNameList.contains(taskName));
    }

    @Then("The task {string} is pending")
    public void taskPending(String taskName) {
        Task task = this.tasks.stream().filter(element -> element.getName().equals(taskName)).collect(Collectors.toList()).get(0);
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @When("I put {string} done")
    public void doneTask(String taskName) {
        Long id = this.tasks.stream().filter(element -> element.getName().equals(taskName)).collect(Collectors.toList()).get(0).getId();
        departmentManager.done(id);
    }

    @And("I get all tasks with done")
    public void getAllTasksWithDone() {
        this.tasks = departmentManager.getTasks();
    }

    @Then("I have a {string} mishap done")
    public void taskDone(String taskName) {
        Task task = this.tasks.stream().filter(element -> element.getName().equals(taskName)).collect(Collectors.toList()).get(0);
        assertEquals(TaskStatus.FINISHED, task.getStatus());
    }

}
