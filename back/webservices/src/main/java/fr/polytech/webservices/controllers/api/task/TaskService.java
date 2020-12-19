package fr.polytech.webservices.controllers.api.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.task.components.TaskAction;
import fr.polytech.task.errors.TaskNotFound;
import fr.polytech.task.models.Task;
import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@ComponentScan({ "fr.polytech.task" })
@RequestMapping("/api/task")
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    TaskAction taskAction;

    @CrossOrigin
    @PutMapping("/{id}")
    public Task endTask(@PathVariable(value = "id") Long id) {
        log.info("PUT : /api/task/" + id);
        try {
            return taskAction.endTask(id);
        } catch (TaskNotFound e) {
            throw new ResourceNotFoundException();
        }
    }
    
}
