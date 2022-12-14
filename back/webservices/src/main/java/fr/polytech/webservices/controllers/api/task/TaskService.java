package fr.polytech.webservices.controllers.api.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.task.components.TaskManager;
import fr.polytech.task.models.Task;
import fr.polytech.webservices.Application;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@ComponentScan({ "fr.polytech.task" })
@RequestMapping("/api/task")
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    TaskManager taskManager;

    @CrossOrigin
    @GetMapping("")
    public List<Task> getTasks() {
        log.info("GET : /api/task/");
        return taskManager.getTasks();
    }
    
}
