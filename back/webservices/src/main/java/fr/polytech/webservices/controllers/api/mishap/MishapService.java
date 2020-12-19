package fr.polytech.webservices.controllers.api.mishap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.mishap.components.MishapManager;
import fr.polytech.mishap.errors.MishapNotFound;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.webservices.models.MishapBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@ComponentScan({ "fr.polytech.mishap" })
@RequestMapping("/api/mishap")
public class MishapService {

    @Autowired
    MishapManager mishapManager;

    @CrossOrigin
    @PostMapping("")
    public Mishap createMishap(@RequestBody MishapBody mishap) {
        return mishapManager.createMishap(mishap.name, mishap.type, mishap.priority);
    }

    @CrossOrigin
    @GetMapping("")
    public List<Mishap> getMishaps() {
        return mishapManager.getMishaps();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Mishap getMishapById(@PathVariable Long id) {
        try {
            return mishapManager.getMishapById(id);
        } catch (MishapNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Mishap updateMishap(@PathVariable Long id, @RequestBody MishapBody mishap){
        try {
            return mishapManager.updateMishap(id, mishap.name, mishap.type, mishap.priority);
        } catch (MishapNotFound e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public String deleteMishap(@PathVariable Long id) {
        mishapManager.deleteMishap(id);
        return "OK";
    }

}
