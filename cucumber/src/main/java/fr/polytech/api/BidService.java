package fr.polytech.api;

import fr.polytech.models.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BidService {

    @Autowired
    private Api api;


    public List<Bid> getBids() {
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method and default Headers.
        Bid[] array = restTemplate.getForObject(String.format("http://%s:%s/api/bid", api.getHost(), api.getPort()), Bid[].class);
        List<Bid> bid = new ArrayList<>(Arrays.asList(array));

        return bid;
    }

    public Bid getBidById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Bid bid = restTemplate.getForObject(String.format("http://%s:%s/api/bid/" + id, api.getHost(), api.getPort()), Bid.class);
        return bid;
    }
}
