package fr.polytech.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.polytech.models.Supplier;
import fr.polytech.models.bid.Bid;
import fr.polytech.models.bid.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.polytech.models.Task;

@Service
public class SupplierService {

    @Autowired
    private Api api;

    public List<Task> getTasks() {
        RestTemplate restTemplate = new RestTemplate();
        // Send request with GET method and default Headers.
        Task[] array = restTemplate.getForObject(String.format("http://%s:%s/api/task", api.getHost(), api.getPort()), Task[].class);
        List<Task> tasks = new ArrayList<>(Arrays.asList(array));

        return tasks;
    }

    public void done(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(String.format("http://%s:%s/api/task/%d", api.getHost(), api.getPort(), id), new Object[]{});
    }

    public List<Supplier> getSuppliers() {
        RestTemplate restTemplate = new RestTemplate();
        // Send request with GET method and default Headers.
        Supplier[] array = restTemplate.getForObject(String.format("http://%s:%s/api/supplier", api.getHost(), api.getPort()), Supplier[].class);
        List<Supplier> suppliers = new ArrayList<>(Arrays.asList(array));
        return suppliers;
    }


    public Offer outbid(long bidId, Offer offerBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Offer> requestBody = new HttpEntity<>(offerBody, headers);

        // Send request with POST method.
        return restTemplate.postForObject(
                String.format("http://%s:%s/api/supplier/%d/outbid", api.getHost(),
                        api.getPort(), bidId),
                requestBody,
                Offer.class);
    }

    public Bid getBid(long id) {
        RestTemplate restTemplate = new RestTemplate();
        // Send request with GET method and default Headers.
        return restTemplate.getForObject(String.format("http://%s:%s/api/bid/%d", api.getHost(), api.getPort(), id), Bid.class);
    }
}
