package fr.polytech.webservices.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.task.components.TaskAction;
import fr.polytech.task.models.Task;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
@ComponentScan({ "fr.polytech.task" })
public class TaskService {

    @Autowired
    TaskAction taskAction;

    @CrossOrigin
	@GetMapping("/task")
	public ResponseEntity<Task> getTask() {
        Task task = taskAction.getTask();
		return ok().body(task);
    }
    
    @CrossOrigin
	@PutMapping("/task")
	public ResponseEntity<Task> endTask() {
        taskAction.endTask();
		return ok().body(taskAction.getTask());
    }
    
}
