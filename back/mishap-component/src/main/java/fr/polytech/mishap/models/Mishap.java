package fr.polytech.mishap.models;

import javax.persistence.Entity;

import java.util.Date;
import java.util.Objects;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskStatus;

@Entity
public class Mishap extends Task {


    public Mishap(){}

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mishap)) {
            return false;
        }
        Mishap mishap = (Mishap) o;
        return Objects.equals(this.name, mishap.name)
                && Objects.equals(this.type, mishap.type)
                && Objects.equals(this.desiredDate, mishap.desiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, desiredDate);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", desiredDate='" + getDesiredDate() + "'" +
            "}";
    }

}
