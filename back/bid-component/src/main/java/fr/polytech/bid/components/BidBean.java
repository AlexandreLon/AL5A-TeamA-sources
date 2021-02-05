package fr.polytech.bid.components;

import fr.polytech.bid.errors.BidNotClosedException;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;
import fr.polytech.bid.models.OfferStatus;
import fr.polytech.bid.models.BidStatus;
import fr.polytech.bid.repositories.BidRepository;
import fr.polytech.bid.repositories.OfferRepository;
import fr.polytech.supplierregistry.components.SupplierProvider;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import fr.polytech.task.models.Task;

import fr.polytech.task.models.TaskType;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan({"fr.polytech.bid.repositories", "fr.polytech.supplierregistry.components"})
@EntityScan("fr.polytech.bid.models")
@EnableJpaRepositories("fr.polytech.bid.repositories")
public class BidBean implements BidViewer, BidCreator, BidProposer, BidManager {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SupplierProvider supplierProvider;

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
        bid.setName(task.getName());
        bid.setTask(task);
        bid.setDesiredDate(desiredDate);
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

        Supplier supplier = supplierProvider.getSupplierById(supplierId);

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

    @Override
    public Offer acceptOffer(Long id) throws OfferNotFoundException {
        Optional<Offer> opt = offerRepository.findById(id);
        if (!opt.isPresent())
            throw new OfferNotFoundException();
        Offer offerToAccept = opt.get();
        Bid associatedBid = offerToAccept.getBid();
        associatedBid.setStatus(BidStatus.CLOSED);
        associatedBid.getTask().setRealizationDate(offerToAccept.getProposedDate());
        associatedBid.getTask().setPrice(offerToAccept.getPrice());
        offerToAccept.getSupplier().getTasks().add(associatedBid.getTask());
        offerToAccept.setStatus(OfferStatus.ACCEPTED);
        offerToAccept = offerRepository.save(offerToAccept);
        List<Offer> offersToReject = offerRepository.findByBidId(associatedBid.getId());
        for (Offer off : offersToReject) {
            if (off.getId() != offerToAccept.getId()) {
                off.setStatus(OfferStatus.REJECTED);
                offerRepository.save(off);
            }
        }
        return offerToAccept;
    }

    @Override
    public Offer getAcceptedOffer(Long id) throws BidNotFoundException, BidNotClosedException {
        Optional<Bid> opt = bidRepository.findById(id);
        if (!opt.isPresent()) {
            throw new BidNotFoundException();
        }
        Bid bid = opt.get();
        if (bid.getStatus() != BidStatus.CLOSED) {
            throw new BidNotClosedException();
        }
        return offerRepository.findByBidIdAndStatus(bid.getId(), OfferStatus.ACCEPTED);
    }

    @Override
    public List<Bid> getBidsBySupplierId(long supplierId) throws SupplierNotFoundException {
        TaskType supplierTaskType = supplierProvider.getSupplierTaskTypeById(supplierId);
        return bidRepository.findByTaskType(supplierTaskType);
    }
}