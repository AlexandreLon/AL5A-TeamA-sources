package fr.polytech.api;

/*
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Mishap;
*/

public class MishapManager {

	public void createMishap(String mishapName, String mishapType) {
		
		/*
		Mishap mishap = new Mishap();
		mishap.setName(mishapName);
		mishap.setType(mishapType);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
	
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Mishap> requestBody = new HttpEntity<>(mishap, headers);
 
		// Send request with POST method.
		restTemplate.postForObject(String.format("http://%s:%s/mishap", Api.HOST, Api.PORT), requestBody, Mishap.class);
		*/
	}

}
