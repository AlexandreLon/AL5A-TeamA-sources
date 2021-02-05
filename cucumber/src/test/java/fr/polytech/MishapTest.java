package fr.polytech;

import fr.polytech.api.SupplierService;
import fr.polytech.models.MishapPriority;
import fr.polytech.models.Task;
import fr.polytech.models.mishap.Mishap;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.polytech.api.MishapService;
import fr.polytech.models.TaskStatus;
import fr.polytech.models.TaskType;

@SpringBootTest
public class MishapTest {

    @ParameterType("LOW|MEDIUM|HIGH")
    public MishapPriority mishapPriority(String priority){
        switch(priority){
            case "LOW": return MishapPriority.LOW;
            case "MEDIUM": return MishapPriority.MEDIUM;
            case "HIGH": return MishapPriority.HIGH;
            default: return null;
        }
    }

    private String mishapName;
    private List<Task> tasks;
    private Mishap mishap;

    @Autowired
    private MishapService mishapService;

    @Autowired
    private SupplierService supplierService;

    @Given("A {string} mishap")
    public void aMishapName(String mishapName) {
        this.mishapName = mishapName;
    }

    @When("I create a mishap of type {taskType} and priority {mishapPriority}")
    public void createMishap(TaskType mishapType, MishapPriority mishapPriority) {
        mishap = mishapManager.createMishap(mishapName, mishapType, mishapPriority);
    }

    @And("I get all tasks")
    public void getAllTasks() {
        this.tasks = supplierService.getTasks();
    }

    @Then("I have a {string} mishap in tasks")
    public void taskAdded(String taskName) {
        List<String> taskNameList = this.tasks.stream().map(Task::getName).collect(Collectors.toList());
        assertTrue(taskNameList.contains(taskName));
    }

    @Then("The task {string} is waiting for bid closure")
    public void taskWaitingForBidClosure(String taskName) {
        Task task = this.tasks.stream().filter(element -> element.getId().equals(mishap.getId())).collect(Collectors.toList()).get(0);
        assertEquals(TaskStatus.WAITING_FOR_BID_CLOSURE, task.getStatus());
    }

    @When("I finish the {string} task")
    public void doneTask(String taskName) {
        Long id = this.tasks.stream().filter(element -> element.getName().equals(taskName)).collect(Collectors.toList()).get(0).getId();
        supplierService.done(id);
    }

    @And("I get all tasks again")
    public void getAllTasksAgain() {
        this.tasks = supplierService.getTasks();
    }

    @Then("the mishap named {string} is finished")
    public void taskDone(String taskName) {
        Task task = this.tasks.stream().filter(element -> element.getName().equals(taskName)).collect(Collectors.toList()).get(0);
        assertEquals(mishapName, task.getName());
        assertEquals(TaskStatus.FINISHED, task.getStatus());
    }

}
