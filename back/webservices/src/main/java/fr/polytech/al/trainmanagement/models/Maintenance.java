package fr.polytech.al.trainmanagement.models;

public class Maintenance {
    
    private int id;
    private String name;
    private String type;
    private String status;
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Maintenance(String name, String type, String status) {
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
