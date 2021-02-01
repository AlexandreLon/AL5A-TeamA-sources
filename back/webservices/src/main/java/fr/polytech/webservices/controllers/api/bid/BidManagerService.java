package fr.polytech.webservices.controllers.api.bid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.webservices.Application;
import fr.polytech.webservices.errors.ResourceNotFoundException;
import fr.polytech.bid.components.BidManager;
import fr.polytech.bid.errors.OfferNotFoundException;
import fr.polytech.bid.models.Offer;

@RestController
@ComponentScan({ "fr.polytech.bid" })
@RequestMapping("/api/bid")
public class BidManagerService {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    BidManager bidManager;

    @CrossOrigin
    @PutMapping("/{idOffer}/accept")
    public Offer acceptOffer(@PathVariable Long idOffer){
        log.info("GET : /api/bid/accept/" + idOffer);
        try {
            return bidManager.acceptOffer(idOffer);
        } catch (OfferNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }
}
