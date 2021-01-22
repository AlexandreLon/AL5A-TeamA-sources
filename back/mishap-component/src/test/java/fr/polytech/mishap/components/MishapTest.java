package fr.polytech.mishap.components;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import fr.polytech.mishap.errors.MishapNotFound;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.task.models.TaskPriority;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MishapTest {

    @Autowired
    private MishapManager mishapManager;

    @Test
    public void createMishapTest() {
        // Mockito.when(mishapRepository.save(any(Mishap.class))).thenAnswer(invocation -> (Mishap)invocation.getArguments()[0]);
        Mishap mishap = mishapManager.createMishap("foo", "bar", new Date(), TaskPriority.HIGH);
        List<Mishap> mishaps = mishapManager.getMishaps().stream().filter(e -> e.getName().equals("foo")).collect(Collectors.toList());
        assertEquals(1, mishaps.size());
        Mishap gettingMishap = mishaps.get(0);
        assertEquals(mishap, gettingMishap);
    }

    @Test
    public void getMishapByIdExist() {
        Mishap mishap = mishapManager.createMishap("foo", "bar", new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.getMishapById(mishap.getId());
            assertEquals(mishap, gettingMishap);
		});
    }

    @Test
    public void getMishapByIdDoesntExist() {
        assertThrows(MishapNotFound.class, () -> {
			mishapManager.getMishapById(100000l);
		});
    }

    @Test
    public void updateMishapExist() {
        Mishap mishap = mishapManager.createMishap("foo", "bar", new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.updateMishap(mishap.getId(), "foo2", "bar2", TaskPriority.LOW);
            mishap.setName("foo2");
            mishap.setType("bar2");
            assertEquals(mishap, gettingMishap);
		});
    }

    @Test
    public void updateMishapDoentExist() {
        assertThrows(MishapNotFound.class, () -> {
			mishapManager.updateMishap(100000l, "foo", "bar", TaskPriority.HIGH);
		});
    }

    @Test
    public void deleteMishapTest() {
        Mishap mishap = mishapManager.createMishap("foo", "bar", new Date(), TaskPriority.HIGH);
        assertDoesNotThrow(() -> {
            Mishap gettingMishap = mishapManager.getMishapById(mishap.getId());
            assertEquals(mishap, gettingMishap);
        });
        mishapManager.deleteMishap(mishap.getId());
        assertThrows(MishapNotFound.class, () -> {
			mishapManager.getMishapById(mishap.getId());
		});
    }
}
