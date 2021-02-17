package fr.polytech.webservices.controllers.api.mishap;

import fr.polytech.webservices.errors.BadRequestException;
import fr.polytech.webservices.models.MishapCreationBody;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.maintenance.components.MishapManager;
import fr.polytech.maintenance.errors.MishapNotFoundException;
import fr.polytech.maintenance.models.Mishap;
import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.webservices.models.MishapBody;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@ComponentScan({ "fr.polytech.mishap" })
@RequestMapping("/api/mishap")
public class MishapService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    MishapManager mishapManager;

    @CrossOrigin
    @PostMapping("")
    public Mishap createMishap(@RequestBody MishapCreationBody mishapCreation) {
        log.info("POST : /api/mishap/");
        try{
            return mishapManager.createMishap(mishapCreation.name, mishapCreation.type, mishapCreation.desiredDate, mishapCreation.priority);
        }
        catch (IllegalArgumentException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("")
    public List<Mishap> getMishaps() {
        log.info("GET : /api/mishap/");
        return mishapManager.getMishaps();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Mishap getMishapById(@PathVariable Long id) {
        log.info("GET : /api/mishap/" + id);
        try {
            return mishapManager.getMishapById(id);
        } catch (MishapNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Mishap updateMishap(@PathVariable Long id, @RequestBody MishapBody mishap){
        log.info("PUT : /api/mishap/" + id);
        try {
            return mishapManager.updateMishap(id, mishap.name, mishap.type, mishap.priority);
        } catch (MishapNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @PutMapping("/{id}/abort")
    public String abortMishap(@PathVariable Long id) {
        log.info("PUT : /api/mishap/" + id + "/abort");
        try {
            mishapManager.abortMishap(id);
        } catch (MishapNotFoundException e) {
            throw new ResourceNotFoundException();
        }
        return "OK";
    }

}
