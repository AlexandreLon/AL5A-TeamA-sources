package fr.polytech.webservices.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.webservices.Application;

@RestController
public class APIService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    class Success {
        public String status = "success";
    }
    
    @CrossOrigin
    @GetMapping("/api")
    public Success api() {
        log.info("GET : /api");
        return new Success();
    }
}