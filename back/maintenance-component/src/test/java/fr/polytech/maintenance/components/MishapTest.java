package fr.polytech.maintenance.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import fr.polytech.notification.components.NotificationProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import fr.polytech.maintenance.errors.MishapNotFoundException;
import fr.polytech.maintenance.models.Mishap;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.models.TaskType;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MishapTest {

    @Autowired
    private MishapManager mishapManager;

    @MockBean
    private NotificationProducer notificationProducer;

    @Test
    public void createMishapTest() {
        // Mockito.when(mishapRepository.save(any(Mishap.class))).thenAnswer(invocation -> (Mishap)invocation.getArguments()[0]);
        Mishap mishap = mishapManager.createMishap("foo", TaskType.VERIFICATION, new Date(), TaskPriority.HIGH);
        List<Mishap> mishaps = mishapManager.getMishaps().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, mishaps.size());
        Mishap gettingMishap = mishaps.get(0);
        assertEquals(mishap, gettingMishap);
    }

    @Test
    public void getMishapByIdExist() {
        Mishap mishap = mishapManager.createMishap("foo", TaskType.VERIFICATION, new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.getMishapById(mishap.getId());
            assertEquals(mishap, gettingMishap);
		});
    }

    @Test
    public void getMishapByIdDoesntExist() {
        assertThrows(MishapNotFoundException.class, () -> {
			mishapManager.getMishapById(100000l);
		});
    }

    @Test
    public void updateMishapExist() {
        Mishap mishap = mishapManager.createMishap("foo", TaskType.VERIFICATION, new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.updateMishap(mishap.getId(), "foo2", 
                    TaskType.CLEANING, TaskPriority.LOW);
            mishap.setName("foo2");
            mishap.setType(TaskType.CLEANING);
            assertEquals(mishap, gettingMishap);
		});
    }

    @Test
    public void updateMishapDoentExist() {
        assertThrows(MishapNotFoundException.class, () -> {
			mishapManager.updateMishap(100000l, "foo", TaskType.VERIFICATION, TaskPriority.HIGH);
		});
    }

    @Test
    public void deleteMishapTest() {
        Mishap mishap = mishapManager.createMishap("foo", TaskType.VERIFICATION, new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.getMishapById(mishap.getId());
            assertEquals(mishap, gettingMishap);
            mishapManager.abortMishap(mishap.getId());
            gettingMishap = mishapManager.getMishapById(mishap.getId());
            assertEquals(TaskStatus.ABORTED, gettingMishap.getStatus());
        });
    }
}
