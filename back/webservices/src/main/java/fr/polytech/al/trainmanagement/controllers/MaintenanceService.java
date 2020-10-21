package fr.polytech.al.trainmanagement.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fr.polytech.al.trainmanagement.models.MaintenanceBody;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@SpringBootApplication
public class MaintenanceService {


    @CrossOrigin
	@PostMapping("/maintenance")
	public ResponseEntity<MaintenanceBody> createMaintenance(@RequestBody MaintenanceBody maintenance) {
        // Here call the interface
        System.out.println(maintenance.name);
        System.out.println("ucucucucu");
		return ok().body(maintenance);
    }
    

}
