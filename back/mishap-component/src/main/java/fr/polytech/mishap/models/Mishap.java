package fr.polytech.mishap.models;

import javax.persistence.Entity;
import javax.persistence.Column;

import java.util.Date;
import java.util.Objects;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskStatus;

@Entity
public class Mishap extends Task {

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private MishapPriority priority;


    public Mishap(){}

    public Mishap(String name, String type, MishapPriority priority) {
        this.name = name;
        this.type = type;
        this.priority = priority;
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

    public MishapPriority getPriority() {
        return this.priority;
    }

    public void setPriority(MishapPriority priority) {
        this.priority = priority;
    }

    public Mishap name(String name) {
        this.name = name;
        return this;
    }

    public Mishap type(String type) {
        this.type = type;
        return this;
    }

    public Mishap priority(MishapPriority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mishap)) {
            return false;
        }
        Mishap mishap = (Mishap) o;
        return Objects.equals(name, mishap.name) && Objects.equals(type, mishap.type) && Objects.equals(priority, mishap.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, priority);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }

}
