package fr.polytech.models.maintenance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.polytech.models.Task;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Maintenance extends Task {
    
}
