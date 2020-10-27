package fr.polytech;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import fr.polytech.api.TodoGet;
import fr.polytech.models.Todo;

public class TodosTest {

    private Todo[] todos;

    @When("get all todos")
    public void something() {
        todos = new TodoGet().getAllTodo();
    }

    @Then("i have {int} todos")
    public void doSomething(int size) {
        assertEquals(size, todos.length);
    }
}
