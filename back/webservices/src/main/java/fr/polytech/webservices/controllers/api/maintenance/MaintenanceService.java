package fr.polytech.webservices.controllers.api.maintenance;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import fr.polytech.maintenance.components.MaintenanceManager;
import fr.polytech.maintenance.errors.MaintenanceNotFound;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.webservices.models.MaintenanceBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@ComponentScan({ "fr.polytech.maintenance" })
@RequestMapping("/api/maintenance")
public class MaintenanceService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    MaintenanceManager maintenanceManager;

    @CrossOrigin
    @PostMapping("")
    public Maintenance createMaintenance(@RequestBody MaintenanceBody maintenance) {
        log.info("POST : /api/maintenance");
        return maintenanceManager.createMaintenance(maintenance.name, maintenance.type, maintenance.desiredDate);
    }

    @CrossOrigin
    @GetMapping("")
    public List<Maintenance> getMaintenances() {
        log.info("GET : /api/maintenance");
        return maintenanceManager.getMaintenances();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable Long id) {
        log.info("GET : /api/maintenance/" + id);
        try {
            return maintenanceManager.getMaintenanceById(id);
        } catch (MaintenanceNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Maintenance updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceBody maintenance) {
        log.info("PUT : /api/maintenance/" + id);
        try {
            return maintenanceManager.updateMaintenance(id, maintenance.name, maintenance.type);
        } catch (MaintenanceNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteMaintenance(@PathVariable Long id) {
        log.info("DELETE : /api/maintenance/" + id);
        this.maintenanceManager.deleteMaintenance(id);
        return "OK";
    }
}
