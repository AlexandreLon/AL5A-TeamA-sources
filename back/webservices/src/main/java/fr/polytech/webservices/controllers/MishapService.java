package fr.polytech.webservices.controllers;

import fr.polytech.task.models.TaskStatus;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.mishap.components.MishapManager;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.webservices.models.MishapBody;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
@ComponentScan({ "fr.polytech.mishap" })
public class MishapService {

    @Autowired
    MishapManager mishapManager;

    @CrossOrigin
    @PostMapping("/mishap")
    public ResponseEntity<Mishap> createMishap(@RequestBody MishapBody mishap) {
        Mishap mishapCreated = mishapManager.createMishap(mishap.name, mishap.type, mishap.priority);
        return ok().body(mishapCreated);
    }

    @CrossOrigin
    @GetMapping("/mishap")
    public ResponseEntity<List<Mishap>> getMishaps() {
        List<Mishap> mishapsCreated = mishapManager.getMishaps();
        return ok().body(mishapsCreated);
    }

    @CrossOrigin
    @GetMapping("/mishap/{id}")
    public ResponseEntity<Mishap> getMishapById(@PathVariable Long id) {
        Mishap mishapCreated = mishapManager.getMishapById(id);
        return ok().body(mishapCreated);
    }

    @CrossOrigin
    @PutMapping("/mishap/{id}")
    public ResponseEntity<Mishap> updateMishap(@PathVariable Long id, @RequestBody MishapBody mishap){
        Mishap updatedMishap = mishapManager.updateMishap(id, mishap.name, mishap.type, mishap.priority);
        return ok().body(updatedMishap);
    }

    @CrossOrigin
    @DeleteMapping("/mishap/{id}")
    public ResponseEntity<String> deleteMishap(@PathVariable Long id) {
        mishapManager.deleteMishap(id);
        return ok().body("OK");
    }

}
