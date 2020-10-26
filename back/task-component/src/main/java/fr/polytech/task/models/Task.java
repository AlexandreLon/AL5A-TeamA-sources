package fr.polytech.task.models;

import java.util.Date;

public class Task {
    
    protected int id;
    protected String name;
    protected String type;
    protected TaskStatus status;
    protected Date creationDate;

	

    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Task(String name, String type) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.status = TaskStatus.PENDING;
        this.creationDate = new Date();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
