package fr.polytech.task.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

import fr.polytech.task.errors.TaskNotFoundException;
import fr.polytech.task.models.Task;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.repositories.TaskRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan({"fr.polytech.task.repositories", "fr.polytech.task.components"})
@EntityScan("fr.polytech.task.models")
@EnableJpaRepositories("fr.polytech.task.repositories")
public class TaskTest {
    
    @Autowired
    private TaskAction taskAction;

    @Autowired
    private ScheduleVisualizer scheduleVisualizer;

    @Autowired 
    private TaskRepository tr;

    @Test
    public void endTaskTestExist() {
        final Task task = new Task();
        task.setCreationDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.PENDING);
        task.setType("bar");
        tr.save(task);
        assertDoesNotThrow(() -> {
            Task doneTask = taskAction.endTask(task.getId());
            assertEquals(TaskStatus.FINISHED, doneTask.getStatus());
		});
        
    }

    @Test
    public void endTaskTestDoesntExist() {
        assertThrows(TaskNotFoundException.class, () -> {
			taskAction.endTask(10000l);
		});
        
    }

    @Test
    public void scheduleVisualizerTest() {
        Task task = new Task();
        task.setCreationDate(new Date());
        task.setName("foo");
        task.setPriority(TaskPriority.HIGH);
        task.setStatus(TaskStatus.PENDING);
        task.setType("bar");
        assertEquals(0, scheduleVisualizer.getPlanning().size());
        task = tr.save(task);
        assertEquals(1, scheduleVisualizer.getPlanning().size());
        assertEquals(task, scheduleVisualizer.getPlanning().get(0));
    }

}
