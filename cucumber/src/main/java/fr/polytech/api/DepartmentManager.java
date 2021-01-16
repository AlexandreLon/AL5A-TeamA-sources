package fr.polytech.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Task;

public class DepartmentManager {

	public List<Task> getTasks() {
		RestTemplate restTemplate = new RestTemplate();
 
        // Send request with GET method and default Headers.
        Task[] array = restTemplate.getForObject(String.format("http://%s:%s/api/schedule", Api.HOST, Api.PORT), Task[].class);
		List<Task> tasks = new ArrayList<>(Arrays.asList(array));

        return tasks;
	}

	public void done(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(String.format("http://%s:%s/api/task/%d", Api.HOST, Api.PORT, id), new Object[] {});
	}
    
}
