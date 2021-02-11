package fr.polytech.supplierregistry.models;

import fr.polytech.task.models.TaskType;
import fr.polytech.task.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "name", length = 30, nullable = false)
    protected String name;

    @Column(name = "task_type", nullable = false)
    protected TaskType taskType;

    @OneToMany(fetch = FetchType.EAGER)
    protected List<Task> tasks;

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

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Supplier)) {
            return false;
        }
        Supplier supplier = (Supplier) o;

        return Objects.equals(id, supplier.id) && Objects.equals(name, supplier.name) && Objects.equals(taskType, supplier.taskType) && new ArrayList<>(getTasks()).equals(new ArrayList<>(supplier.getTasks()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taskType, tasks);
    }

}
