package fr.polytech.task.models;

public class Task {
    
    private int id;
    private String name;
    private String type;
    private TaskStatus status;
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(String name, String type, TaskStatus status) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.status = status;
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

}
