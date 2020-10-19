package fr.polytech.al.trainmanagement.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.al.trainmanagement.models.Task;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@SpringBootApplication
public class TaskService {

    Task task = new Task("Changer les rails", "Changer", "In Progress");

    @CrossOrigin
	@GetMapping("/task")
	public ResponseEntity<Task> getTask() {
		return ok().body(this.task);
    }
    
    @CrossOrigin
	@PutMapping("/task")
	public ResponseEntity<Task> putTask() {
        this.task.setStatus("Done");
		return ok().body(this.task);
    }
    
}
