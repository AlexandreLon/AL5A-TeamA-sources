package fr.polytech.task.components;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.repositories.TaskRepository;
import fr.polytech.task.errors.TaskNotFound;
import fr.polytech.task.models.Task;

@Component
@ComponentScan("fr.polytech.task.repositories")
@EntityScan("fr.polytech.task.models")
@EnableJpaRepositories("fr.polytech.task.repositories")
public class TaskBean implements TaskAction, ScheduleVisualizer {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task endTask(Long id) throws TaskNotFound {
        Optional<Task> opt = taskRepository.findById(id);
        if(opt.isPresent()) {
            Task task = opt.get();
            task.setStatus(TaskStatus.FINISHED);
            task = taskRepository.save(task);
            return task;
        }
        throw new TaskNotFound();
    }
    
	@Override
	public List<Task> getPlanning() {
        return (List<Task>) taskRepository.findAllByOrderByPriorityDesc();
	}
}