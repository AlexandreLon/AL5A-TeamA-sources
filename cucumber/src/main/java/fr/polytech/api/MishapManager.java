package fr.polytech.api;

import fr.polytech.models.Mishap;
import fr.polytech.models.MishapPriority;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class MishapManager {

	private final String mishapUrl = String.format("http://%s:%s/api/mishap", Api.HOST, Api.PORT);
	private final RestTemplate restTemplate = new RestTemplate();

	public Mishap createMishap(String mishapName, String mishapType, MishapPriority priority) {
		Mishap mishap = new Mishap();
		mishap.setName(mishapName);
		mishap.setType(mishapType);
		mishap.setPriority(priority);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Mishap> requestBody = new HttpEntity<>(mishap, headers);

		// Send request with POST method.
		return restTemplate.postForObject(mishapUrl, requestBody, Mishap.class);
	}

}
