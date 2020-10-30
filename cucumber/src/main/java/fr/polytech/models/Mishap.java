package fr.polytech.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mishap extends Task {

    private MishapPriority priority;

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
        return Objects.equals(getName(), mishap.getName()) && Objects.equals(getType(), mishap.getType()) && Objects.equals(priority, mishap.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), priority);
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
