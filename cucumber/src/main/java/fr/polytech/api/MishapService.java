package fr.polytech.api;

import fr.polytech.models.mishap.Mishap;
import fr.polytech.models.MishapPriority;
import fr.polytech.models.TaskType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MishapService extends Api{

	@Autowired 
	private Api api;

	private final RestTemplate restTemplate = new RestTemplate();

	public Mishap createMishap(String mishapName, TaskType mishapType, MishapPriority priority) {
		Mishap mishap = new Mishap();
		mishap.setName(mishapName);
		mishap.setType(mishapType);
		mishap.setPriority(priority);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Mishap> requestBody = new HttpEntity<>(mishap, headers);

		// Send request with POST method.
		return restTemplate.postForObject(String.format("http://%s:%s/api/mishap", api.getHost(), api.getPort()), requestBody, Mishap.class);
	}

}
