package fr.polytech.webservices.controllers.api.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.polytech.task.components.ScheduleVisualizer;
import fr.polytech.task.models.Task;
import fr.polytech.webservices.Application;

@RestController
@ComponentScan({ "fr.polytech.schedule" })
@RequestMapping("/api/schedule")
public class ScheduleService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    ScheduleVisualizer scheduleVisualizer;

    @CrossOrigin
    @GetMapping("")
    public List<Task> getPlanning() {
        log.info("GET : /api/schedule/");
        return scheduleVisualizer.getPlanning();
    }
}
