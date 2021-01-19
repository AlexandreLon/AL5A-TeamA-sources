package fr.polytech.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Api {

    @Value("${api.host}")
    private String host;

    @Value("${api.port}")
    private String port;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
    
}
