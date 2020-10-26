package fr.polytech.webservices.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.polytech.task.components.ScheduleVisualizer;
import fr.polytech.task.models.Task;

@RestController
@SpringBootApplication
@ComponentScan({ "fr.polytech.schedule" })
public class ScheduleService {

    @Autowired
    ScheduleVisualizer scheduleVisualizer;

    @CrossOrigin
    @GetMapping("/schedule")
    public ResponseEntity<List<Task>> getPlanning() {
        return ok().body(scheduleVisualizer.getPlanning());
    }
}
