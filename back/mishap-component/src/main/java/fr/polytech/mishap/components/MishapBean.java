package fr.polytech.mishap.components;

import com.google.inject.internal.util.Lists;
import fr.polytech.bid.components.BidCreator;
import fr.polytech.mishap.errors.MishapNotFoundException;
import fr.polytech.mishap.models.Mishap;
import fr.polytech.mishap.repositories.MishapRepository;

import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.TaskPriority;
import fr.polytech.task.models.TaskStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"fr.polytech.mishap.repositories", "fr.polytech.bid.components","fr.polytech.supplierregistry.repositories"})
@EntityScan("fr.polytech.mishap.models")
@EnableJpaRepositories("fr.polytech.mishap.repositories")
public class MishapBean implements MishapManager {

    @Autowired
    private MishapRepository mishapRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BidCreator bidCreator;

    @Override
    public Mishap createMishap(String name, String type, Date desiredDate, TaskPriority priority) {
        Mishap mishap = new Mishap();
        mishap.setName(name);
        mishap.setType(type);
        mishap.setPriority(priority);
        mishap.setStatus(TaskStatus.PENDING);
        mishap.setCreationDate(new Date());
        mishap = mishapRepository.save(mishap);
        bidCreator.createBid(mishap, Lists.newArrayList(supplierRepository.findAll()), desiredDate);
        return mishap;
    }

    @Override
    public List<Mishap> getMishaps() {
        return (List<Mishap>) this.mishapRepository.findAll();
    }

    @Override
    public Mishap getMishapById(Long id) throws MishapNotFoundException {
        Optional<Mishap> mishap = this.mishapRepository.findById(id);
        if(!mishap.isPresent()) throw new MishapNotFoundException();
        return mishap.get();
    }

    @Override
    public Mishap updateMishap(Long id, String name, String type, TaskPriority priority) throws MishapNotFoundException {
        Optional<Mishap> opt = this.mishapRepository.findById(id);
        if (opt.isPresent()) {
            Mishap mishap = opt.get();
            mishap.setName(name);
            mishap.setType(type);
            mishap.setPriority(priority);
            mishap = this.mishapRepository.save(mishap);
            return mishap;
        }
        throw new MishapNotFoundException();
    }

    @Override
    public void deleteMishap(Long id) {
        this.mishapRepository.deleteById(id);
    }
}
