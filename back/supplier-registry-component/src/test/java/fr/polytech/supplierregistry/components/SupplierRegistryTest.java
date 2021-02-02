package fr.polytech.supplierregistry.components;


import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.TaskType;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ComponentScan("fr.polytech.supplierregistry.repositories")
@EntityScan("fr.polytech.supplierregistry.models")
@EnableJpaRepositories("fr.polytech.supplierregistry.repositories")
public class SupplierRegistryTest {

    @Autowired
    SupplierRepository sr;

    @Autowired
    SupplierAuthenticator sAuthenticator;

    Supplier createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("toto");
        supplier.setTaskType(TaskType.CLEANING);
        supplier.setTasks(new ArrayList<>());
        return supplier;
    }

    @Test
    void getSupplierByIdExist() {
        assertDoesNotThrow(() -> {
			Supplier s = createSupplier();
            sr.save(s);
            assertEquals(s, sAuthenticator.getSupplierById(s.getId()));
		});
    }

    @Test
    void getSupplierByIdDoesntExist() {
        assertThrows(SupplierNotFoundException.class,() -> {
            sAuthenticator.getSupplierById(10000l);
		});
    }
}
