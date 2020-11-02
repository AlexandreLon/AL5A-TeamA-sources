package fr.polytech.webservices.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.polytech.maintenance.components.MaintenanceManager;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.webservices.models.MaintenanceBody;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@SpringBootApplication
@ComponentScan({"fr.polytech.maintenance"})
public class MaintenanceService {

    @Autowired
    MaintenanceManager maintenanceManager;

    @CrossOrigin
    @PostMapping("/maintenance")
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody MaintenanceBody maintenance) {
        Maintenance maintenanceCreated = maintenanceManager.createMaintenance(maintenance.name, maintenance.type);
        return ok().body(maintenanceCreated);
    }

    @CrossOrigin
    @GetMapping("/maintenance")
    public ResponseEntity<List<Maintenance>> getMaintenances() {
        List<Maintenance> maintenances = maintenanceManager.getMaintenances();
        return ok().body(maintenances);
    }

    @CrossOrigin
    @GetMapping("/maintenance/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        Maintenance maintenance = maintenanceManager.getMaintenanceById(id);
        return ok().body(maintenance);
    }

    @CrossOrigin
    @PutMapping("/maintenance/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceBody maintenance) {
        Maintenance updatedMaintenance = maintenanceManager.updateMaintenance(id, maintenance.name, maintenance.type);
        return ok().body(updatedMaintenance);
    }

    @CrossOrigin
    @DeleteMapping("/maintenance/{id}")
    public ResponseEntity<String> deleteMaintenance(@PathVariable Long id) {
        this.maintenanceManager.deleteMaintenance(id);
        return ok().body("OK");
    }
}
