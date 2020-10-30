package fr.polytech.mishap.components;

import fr.polytech.mishap.models.Mishap;
import fr.polytech.mishap.models.MishapPriority;
import fr.polytech.mishap.repositories.MishapRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Component
@ComponentScan("fr.polytech.mishap.repositories")
@EntityScan("fr.polytech.mishap.models")
@EnableJpaRepositories("fr.polytech.mishap.repositories")
public class MishapBean implements MishapManager {

    @Autowired
    private MishapRepository mishapRepository;

    @Override
    public Mishap createMishap(String name, String type, MishapPriority priority) {
        Mishap mishap = new Mishap(name, type, priority);
        mishapRepository.save(mishap);
        return mishap;
    }

    @Override
    public List<Mishap> getMishaps() {
        List<Mishap> mishaps = new ArrayList<>();
        mishapRepository.findAll().forEach(mishaps::add);
        return mishaps;
    }

    @Override
    public Mishap getMishapById(Long id){
        return mishapRepository.findById(id).orElse(null);
    }

    @Override
    public Mishap updateMishap(Long id, String name, String type, MishapPriority priority){
        Mishap mishap = mishapRepository.findById(id).orElse(null);
        if(mishap == null){
            return mishap;
        }
        mishap.setName(name);
        mishap.setType(type);
        mishap.setPriority(priority);
        mishapRepository.save(mishap);
        return mishap;
    }

    @Override
    public void deleteMishap(Long id){
        mishapRepository.deleteById(id);
    }
}
