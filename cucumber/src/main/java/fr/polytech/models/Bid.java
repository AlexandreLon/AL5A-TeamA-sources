package fr.polytech.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bid {
    private Long id;
    private String name;
    private Task task;
    private Date desiredDate;


    public Bid(Long id, String name, Task task, Date desiredDate) {
        this.id = id;
        this.name = name;
        this.task = task;
        this.desiredDate = desiredDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Task getTask() {
        return task;
    }

    public Date getDesiredDate() {
        return desiredDate;
    }
}
