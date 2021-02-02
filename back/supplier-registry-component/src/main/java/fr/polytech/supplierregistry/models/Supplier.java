package fr.polytech.supplierregistry.models;

import fr.polytech.task.models.TaskType;
import fr.polytech.task.models.Task;
import java.util.List;

import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "name", length = 30, nullable = false)
    protected String name;

    @Column(name = "task_type")
    protected TaskType taskType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
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

}
