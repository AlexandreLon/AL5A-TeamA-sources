package fr.polytech.webservices.controllers.api.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.polytech.task.components.ScheduleVisualizer;
import fr.polytech.task.models.Task;

@RestController
@ComponentScan({ "fr.polytech.schedule" })
@RequestMapping("/api/schedule")
public class ScheduleService {

    @Autowired
    ScheduleVisualizer scheduleVisualizer;

    @CrossOrigin
    @GetMapping("")
    public List<Task> getPlanning() {
        return scheduleVisualizer.getPlanning();
    }
}
