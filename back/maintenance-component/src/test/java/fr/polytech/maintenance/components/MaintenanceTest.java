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

import fr.polytech.maintenance.errors.MaintenanceNotFoundException;
import fr.polytech.maintenance.models.Maintenance;
import fr.polytech.task.models.TaskStatus;
import fr.polytech.task.models.TaskType;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MaintenanceTest {

    @Autowired
    private MaintenanceManager maintenanceManager;

    @MockBean
    private NotificationProducer notificationProducer;

    @Test
    public void createMaintenanceTest() {
        // Mockito.when(maintenanceRepository.save(any(Maintenance.class))).thenAnswer(invocation -> (Maintenance)invocation.getArguments()[0]);
        Maintenance maintenance = maintenanceManager.createMaintenance("foo", TaskType.VERIFICATION, new Date());
        List<Maintenance> maintenances = maintenanceManager.getMaintenances().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, maintenances.size());
        Maintenance gettingMaintenance = maintenances.get(0);
        assertEquals(maintenance, gettingMaintenance);
    }

    @Test
    public void getMaintenanceByIdExist() {
        Maintenance maintenance = maintenanceManager.createMaintenance("foo", TaskType.VERIFICATION, new Date());
        assertDoesNotThrow(() -> {
            Maintenance gettingMaintenance = maintenanceManager.getMaintenanceById(maintenance.getId());
            assertEquals(maintenance, gettingMaintenance);
		});
    }

    @Test
    public void getMaintenanceByIdDoesntExist() {
        assertThrows(MaintenanceNotFoundException.class, () -> {
			maintenanceManager.getMaintenanceById(100000l);
		});
    }

    @Test
    public void updateMaintenanceExist() {
        Maintenance maintenance = maintenanceManager.createMaintenance("foo", TaskType.VERIFICATION, new Date());
        assertDoesNotThrow(() -> {
            Maintenance gettingMaintenance = maintenanceManager.updateMaintenance(maintenance.getId(), "foo2", TaskType.REPLACING);
            maintenance.setName("foo2");
            maintenance.setType(TaskType.REPLACING);
            assertEquals(maintenance, gettingMaintenance);
		});
    }

    @Test
    public void updateMaintenanceDoentExist() {
        assertThrows(MaintenanceNotFoundException.class, () -> {
			maintenanceManager.updateMaintenance(100000l, "foo", TaskType.VERIFICATION);
		});
    }

    @Test
    public void abortMaintenanceTest() {
        Maintenance maintenance = maintenanceManager.createMaintenance("foo", TaskType.VERIFICATION, new Date());
        assertDoesNotThrow(() -> {
            Maintenance gettingMaintenance = maintenanceManager.getMaintenanceById(maintenance.getId());
            assertEquals(maintenance, gettingMaintenance);
            maintenanceManager.abortMaintenance(maintenance.getId());
            gettingMaintenance = maintenanceManager.getMaintenanceById(maintenance.getId());
            assertEquals(TaskStatus.ABORTED, gettingMaintenance.getStatus());
        });
        
    }
}
