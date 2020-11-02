package fr.polytech.mishap.models;

import javax.persistence.Entity;
import javax.persistence.Column;

import java.util.Date;
import java.util.Objects;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;

@Entity
public class Mishap extends Task {


    public Mishap(){}

    public Mishap(String name, String type) {
        this.name = name;
        this.type = type;
        this.status = TaskStatus.PENDING;
        this.creationDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mishap)) {
            return false;
        }
        Mishap mishap = (Mishap) o;
        return Objects.equals(name, mishap.name) && Objects.equals(type, mishap.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }

}
