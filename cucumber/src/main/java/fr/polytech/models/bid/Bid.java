package fr.polytech.models.bid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.polytech.models.Task;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bid {
    private Long id;
    private String name;
    private Task task;
    private Date desiredDate;

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
    }
}
