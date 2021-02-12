package fr.polytech.api;

import fr.polytech.models.maintenance.MaintenanceCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.TaskType;
import fr.polytech.models.maintenance.Maintenance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceService {

	@Autowired 
	private Api api;

	public Maintenance createMaintenance(String maintenanceName, TaskType maintenanceType, Date desiredDate) {
		MaintenanceCreation maintenance = new MaintenanceCreation();
		maintenance.setName(maintenanceName);
		maintenance.setType(maintenanceType);
		maintenance.setDesiredDate(desiredDate);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Maintenance> requestBody = new HttpEntity<>(maintenance, headers);
 
		// Send request with POST method.
		return restTemplate.postForObject(String.format("http://%s:%s/api/maintenance", api.getHost(), api.getPort()), requestBody, Maintenance.class);
	}

	public List<Maintenance> getMaintenances(){
		RestTemplate restTemplate = new RestTemplate();

		// Send request with GET method and default Headers.
		Maintenance[] array = restTemplate.getForObject(String.format("http://%s:%s/api/maintenance", api.getHost(), api.getPort()), Maintenance[].class);
		List<Maintenance> maintenances = new ArrayList<>(Arrays.asList(array));

		return maintenances;
	}

	public Maintenance getMaintenanceByID(Long id){
		RestTemplate restTemplate = new RestTemplate();
		Maintenance maintenance = restTemplate.getForObject(String.format("http://%s:%s/api/maintenance/" + id, api.getHost(), api.getPort()), Maintenance.class);
		return maintenance;
	}
}
