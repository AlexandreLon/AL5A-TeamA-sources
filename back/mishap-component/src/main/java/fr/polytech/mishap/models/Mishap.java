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
    private MishapPriority priority;

    public Mishap(){}

    public Mishap(String name, String type, MishapPriority priority) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.creationDate = new Date();
    }

    public MishapPriority getPriority() {
        return this.priority;
    }

    public void setPriority(MishapPriority priority) {
        this.priority = priority;
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
