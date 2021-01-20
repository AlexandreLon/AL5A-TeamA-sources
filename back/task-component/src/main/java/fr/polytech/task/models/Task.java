package fr.polytech.task.models;

import java.util.Date;

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

    @Column(nullable = false)
    protected Date desiredDate;

    @Column
    private TaskPriority priority;

    @Column(name = "creationDate")
    protected Date creationDate;
    
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

    public Date getDesiredDate() {
        return this.desiredDate;
    }
    public void setDesiredDate(Date desiredDate) {
        this.desiredDate = desiredDate;
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

}
