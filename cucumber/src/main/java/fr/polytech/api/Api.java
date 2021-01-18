package fr.polytech.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Api {
    
    // public static final String HOST = "localhost";
    // public static final String PORT = "3000";

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
