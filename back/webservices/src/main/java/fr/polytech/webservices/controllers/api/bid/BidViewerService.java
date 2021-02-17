package fr.polytech.webservices.controllers.api.bid;

import fr.polytech.bid.components.BidViewer;
import fr.polytech.bid.errors.BidNotClosedException;
import fr.polytech.bid.errors.BidNotFoundException;
import fr.polytech.bid.models.Bid;
import fr.polytech.bid.models.Offer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;


import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.BadRequestException;
import fr.polytech.webservices.errors.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@ComponentScan({"fr.polytech.bid"})
@RequestMapping("/api/bid")
public class BidViewerService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BidViewer bidViewer;

    @CrossOrigin
    @GetMapping("")
    public List<Bid> getBids() {
        log.info("GET : /api/bid");
        return bidViewer.getBids();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Bid getBidsById(@PathVariable Long id) {
        log.info("GET : /api/bid/" + id);
        try {
            return bidViewer.getBidById(id);
        } catch (BidNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @GetMapping("/{id}/offers")
    public List<Offer> getOffers(@PathVariable Long id) {
        log.info("GET : /api/bid/" + id + "/offers");
        try {
            return bidViewer.getOffers(id);
        } catch (BidNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @CrossOrigin
    @GetMapping("/{idBid}/accepted")
    public Offer getAcceptedOffer(@PathVariable Long idBid) {
        log.info("GET : /api/bid/" + idBid + "/accepted");
        try {
            return bidViewer.getAcceptedOffer(idBid);
        } catch (BidNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (BidNotClosedException e) {
            throw new BadRequestException();
        }
    }

}
