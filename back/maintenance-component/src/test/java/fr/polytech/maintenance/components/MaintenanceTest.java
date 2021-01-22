package fr.polytech.maintenance.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.polytech.maintenance.errors.MaintenanceNotFound;
import fr.polytech.maintenance.models.Maintenance;


@SpringBootTest
public class MaintenanceTest {

    @Autowired
    private MaintenanceManager maintenanceManager;

    @Test
    public void createMaintenanceTest() throws MaintenanceNotFound {
        // Mockito.when(maintenanceRepository.save(any(Maintenance.class))).thenAnswer(invocation -> (Maintenance)invocation.getArguments()[0]);
        Maintenance maintenance = maintenanceManager.createMaintenance("foo", "bar", new Date());
        List<Maintenance> maintenances = maintenanceManager.getMaintenances();
        Maintenance maintenance2 = maintenances.stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList()).get(0);
        assertEquals(maintenance.getName(), maintenance2.getName());
        assertEquals(maintenance.getType(), maintenance2.getType());
    }
}
