package fr.polytech.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Task;
import fr.polytech.models.TaskStatus;

public class DepartmentManager {

	//TODO REMOVE THIS AFTER ADDING MISHAP SYSTEM
	private TaskStatus statusMocked = TaskStatus.PENDING;
	//END REMOVE

	public List<Task> getTasks() {
		RestTemplate restTemplate = new RestTemplate();
 
        // Send request with GET method and default Headers.
        Task[] array = restTemplate.getForObject(String.format("http://%s:%s/schedule", Api.HOST, Api.PORT), Task[].class);
		List<Task> tasks = new ArrayList<>(Arrays.asList(array));

		//TODO REMOVE THIS AFTER ADDING MISHAP SYSTEM
		Task taskMocked = new Task();
		taskMocked.setName("wagon breakdown");
		taskMocked.setStatus(statusMocked);
		tasks.add(taskMocked);
		//END REMOVE

        return tasks;
	}

	public void done(Long id) {
		//TODO REMOVE THIS AFTER ADDING MISHAP SYSTEM
		if(id == null) {
			this.statusMocked = TaskStatus.FINISHED;
			return;
		}
		//END REMOVE
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(String.format("http://%s:%s/task/%d", Api.HOST, Api.PORT, id), new Object[] {});
	}
    
}
