package fr.polytech.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Maintenance;

public class MaintenanceManager {

	public void createMaintenance(String maintenanceName, String maintenanceType) {
		Maintenance maintenance = new Maintenance();
		maintenance.setName(maintenanceName);
		maintenance.setType(maintenanceType);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Maintenance> requestBody = new HttpEntity<>(maintenance, headers);
 
		// Send request with POST method.
		restTemplate.postForObject(String.format("http://%s:%s/api/maintenance", Api.HOST, Api.PORT), requestBody, Maintenance.class);
	}
}
