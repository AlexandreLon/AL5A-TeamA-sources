package fr.polytech.webservices.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.maintenance.components.MaintenanceManager;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.webservices.models.MaintenanceBody;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
@ComponentScan({ "fr.polytech.maintenance" })
public class MaintenanceService {

    @Autowired
    MaintenanceManager maintenanceManager;

    @CrossOrigin
    @PostMapping("/maintenance")
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody MaintenanceBody maintenance) {
        Maintenance maintenanceCreated = maintenanceManager.createMaintenance(maintenance.name, maintenance.type);
        return ok().body(maintenanceCreated);
    }


}
