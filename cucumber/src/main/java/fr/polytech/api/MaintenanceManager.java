package fr.polytech.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Maintenance;

@Service
public class MaintenanceManager {

	@Autowired Api api;

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
		restTemplate.postForObject(String.format("http://%s:%s/api/maintenance", api.getHost(), api.getPort()), requestBody, Maintenance.class);
	}
}
