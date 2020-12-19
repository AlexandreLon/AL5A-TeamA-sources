package fr.polytech.webservices.controllers.api.maintenance;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import fr.polytech.maintenance.components.MaintenanceManager;
import fr.polytech.maintenance.errors.MaintenanceNotFound;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.webservices.models.MaintenanceBody;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@ComponentScan({ "fr.polytech.maintenance" })
@RequestMapping("/api/maintenance")
public class MaintenanceService {

    @Autowired
    MaintenanceManager maintenanceManager;

    @CrossOrigin
    @PostMapping("")
    public Maintenance createMaintenance(@RequestBody MaintenanceBody maintenance) {
        return maintenanceManager.createMaintenance(maintenance.name, maintenance.type);
    }

    @CrossOrigin
    @GetMapping("")
    public List<Maintenance> getMaintenances() {
        return maintenanceManager.getMaintenances();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable Long id) {
        try {
            return maintenanceManager.getMaintenanceById(id);
        } catch (MaintenanceNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Maintenance updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceBody maintenance) {
        try {
            return maintenanceManager.updateMaintenance(id, maintenance.name, maintenance.type);
        } catch (MaintenanceNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteMaintenance(@PathVariable Long id) {
        this.maintenanceManager.deleteMaintenance(id);
        return "OK";
    }
}
