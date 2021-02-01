package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotClosedException;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.bid.errors.SupplierNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.OfferStatus;
import fr.polytech.bid.models.BidStatus;
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
public class BidBean implements BidViewer, BidCreator, BidProposer, BidManager {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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

        Optional<Supplier> optSupplier = supplierRepository.findById(supplierId);
        if (!optSupplier.isPresent())
            throw new SupplierNotFoundException();
        Supplier supplier = optSupplier.get();
        
        offer.setBid(bid);
        offer.setPrice(price);
        offer.setSupplier(supplier);
        offer.setProposedDate(proposedDate);
        offer.setStatus(OfferStatus.PENDING);

        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getOffers(Long id) throws BidNotFoundException {
        Optional<Bid> opt = bidRepository.findById(id);
        if (!opt.isPresent())
            throw new BidNotFoundException();
        Bid bid = opt.get();
        return offerRepository.findByBidId(bid.getId());
    }
<<<<<<< HEAD

    @Override
    public Offer acceptOffer(Long id) throws OfferNotFoundException {
        Optional<Offer> opt = offerRepository.findById(id);
        if (!opt.isPresent())
            throw new OfferNotFoundException();
        Offer offer = opt.get();
        Bid associatedBid = offer.getBid();
        associatedBid.setStatus(BidStatus.CLOSED);
        associatedBid.getTask().setRealizationDate(offer.getProposedDate());
        associatedBid.getTask().setPrice(offer.getPrice());
        offer.getSupplier().addTask(associatedBid.getTask());
        offer.setStatus(OfferStatus.ACCEPTED);
        List<Offer> offersToReject = offerRepository.findByBidId(associatedBid.getId());
        for(Offer off : offersToReject){
            off.setStatus(OfferStatus.REJECTED);
            offerRepository.save(off);
        }
        supplierRepository.save(offer.getSupplier());
        return offerRepository.save(offer);
    }

    @Override
    public Offer getAcceptedOffer(Long id) throws BidNotFoundException, BidNotClosedException {
        Optional<Bid> opt = bidRepository.findById(id);
        if (!opt.isPresent())
            throw new BidNotFoundException();
        Bid bid = opt.get();
        if(bid.getStatus() != BidStatus.CLOSED)
            throw new BidNotClosedException();
        Offer offer = offerRepository.findByBidIdAndStatus(bid.getId(), OfferStatus.ACCEPTED);
        return offer;
    }
}
=======
}
>>>>>>> 264243b (Update pom version to 1.1.2)
