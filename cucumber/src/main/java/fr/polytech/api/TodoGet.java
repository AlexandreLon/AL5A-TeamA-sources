package fr.polytech.api;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Todo;

public class TodoGet {
    
    @Deprecated
    public void getTodos() {
        HttpHeaders headers = new HttpHeaders();
 
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos", //
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();

        System.out.println(result);
    }

    public Todo[] getAllTodo() {
        RestTemplate restTemplate = new RestTemplate();
 
        // Send request with GET method and default Headers.
        Todo[] list = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos", Todo[].class);

        return list;
    }
}
