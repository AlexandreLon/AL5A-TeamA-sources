package fr.polytech.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Task;

@Service
public class SupplierManager {

	@Autowired 
	private Api api;

	public List<Task> getTasks() {
		RestTemplate restTemplate = new RestTemplate();
 
        // Send request with GET method and default Headers.
        Task[] array = restTemplate.getForObject(String.format("http://%s:%s/api/task", api.getHost(), api.getPort()), Task[].class);
		List<Task> tasks = new ArrayList<>(Arrays.asList(array));

        return tasks;
	}

	public void done(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(String.format("http://%s:%s/api/task/%d", api.getHost(), api.getPort(), id), new Object[] {});
	}
    
}
