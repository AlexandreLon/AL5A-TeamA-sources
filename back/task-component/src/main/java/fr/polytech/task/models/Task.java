package fr.polytech.task.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "name", length = 30, nullable = false)
    protected String name;

    @Column(name = "type", length = 30, nullable = false)
    protected String type;

    @Column(name = "status", length = 30, nullable = false)
    protected TaskStatus status;

    @Column
    private TaskPriority priority;

    @Column(name = "creationDate")
    protected Date creationDate;

    @Column(name = "realizationDate", nullable = true)
    protected Date realizationDate;

    @Column(nullable = true)
    private double price;
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public TaskPriority getPriority() {
		return this.priority;
	}
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getRealizationDate() {
        return this.realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(type, task.type) && Objects.equals(status, task.status) && Objects.equals(priority, task.priority) && Objects.equals(creationDate, task.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, status, priority, creationDate);
    }

}
