package fr.polytech.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BidManagerService {

    @Autowired
    private Api api;

    public void accept(int id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestBody = new HttpEntity<>(null, headers);

        // Send request with GET method and default Headers.

        restTemplate.put(String.format("http://%s:%s/api/bid/%d/accept", api.getHost(), api.getPort(), id), requestBody);
    }
}
