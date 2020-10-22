package fr.polytech.webservices.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.polytech.webservices.models.MaintenanceBody;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@SpringBootApplication
public class MaintenanceService {


    @CrossOrigin
	@PostMapping("/maintenance")
	public ResponseEntity<MaintenanceBody> createMaintenance(@RequestBody MaintenanceBody maintenance) {
        // Here call the interface
        return ok().body(maintenance);
    }
    

}
