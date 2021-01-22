package fr.polytech.bid.models;

import fr.polytech.task.models.Task;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "name", length = 30, nullable = false)
    protected String name;

    @OneToOne(targetEntity = Task.class)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    protected Task task;

    @Column
    protected Date desiredDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bid)) {
            return false;
        }
        Bid bid = (Bid) o;
        return Objects.equals(id, bid.id) && Objects.equals(name, bid.name) && Objects.equals(task, bid.task) && Objects.equals(desiredDate, bid.desiredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, task, desiredDate);
    }


}
