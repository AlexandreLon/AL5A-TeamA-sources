package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.BidStatus;
import fr.polytech.bid.models.Supplier;
import fr.polytech.bid.repositories.BidRepository;
import fr.polytech.bid.repositories.OfferRepository;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.supplierregistry.repositories.SupplierRepository;
import fr.polytech.task.models.Task;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan({"fr.polytech.bid.repositories","fr.polytech.supplierregistry.repositories"})
@EntityScan("fr.polytech.bid.models")
@EnableJpaRepositories({"fr.polytech.bid.repositories","fr.polytech.supplierregistry.repositories"})
public class BidBean implements BidViewer, BidCreator, BidProposer {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SupplierRepository supplierReposity;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Bid> getBids() {
        return (List<Bid>) bidRepository.findAll();
    }

    @Override
    public Bid getBidById(Long id) throws BidNotFoundException {
        Optional<Bid> opt = bidRepository.findById(id);
        if (!opt.isPresent())
            throw new BidNotFoundException();
        return opt.get();
    }

    @Override
    public Bid createBid(Task task, List<Supplier> suppliers, Date desiredDate) {
        Bid bid = new Bid();
        bid.setName(task.getName()); // TODO Maybe remove name or how to choose the name of bid ?
        bid.setTask(task);
        bid.setDesiredDate((desiredDate));
        bid.setStatus(BidStatus.ONGOING);
        return bidRepository.save(bid);
    }

    @Override
    public Offer outbid(long bidId, long supplierId, double price, Date proposedDate) throws BidNotFoundException,
            SupplierNotFoundException {
        Offer offer = new Offer();

        Optional<Bid> optBid = bidRepository.findById(bidId);
        if (!optBid.isPresent())
            throw new BidNotFoundException();
        Bid bid = optBid.get();

        Optional<Supplier> optSupplier = supplierReposity.findById(supplierId);
        if (!optSupplier.isPresent())
            throw new SupplierNotFoundException();
        Supplier supplier = optSupplier.get();
        
        offer.setBid(bid);
        offer.setPrice(price);
        offer.setSupplier(supplier);
        offer.setProposedDate(proposedDate);

        return offerRepository.save(offer);
    }
}
