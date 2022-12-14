package fr.polytech.webservices.controllers.api.notification;

import fr.polytech.notification.components.NotificationConsumer;
import fr.polytech.notification.components.NotificationInitializer;
import fr.polytech.webservices.errors.BadRequestException;
import fr.polytech.webservices.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import fr.polytech.webservices.websockets.WebSocketEventListener;

@Controller
@ComponentScan({ "fr.polytech.notification" })
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final MessageSendingOperations<String> messageSendingOperations;

    @Autowired
    NotificationConsumer notificationConsumer;


    @Autowired
    public NotificationService(MessageSendingOperations<String> messageSendingOperations,  NotificationInitializer notificationInitializer ) {
        this.messageSendingOperations = messageSendingOperations;
        notificationInitializer.init(this::sendMessageToTopic);
    }

    @MessageMapping("/register")
    @SendTo("/topic/messages")
    public String registerUser(Message message){
        logger.info("Received a new web message : " + message);
        try {
            notificationConsumer.subscribe(message.getFrom());
            return "subscribed : "+message.getFrom();
        }
        catch (IllegalArgumentException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @MessageMapping("/unsubscribe")
    @SendTo("/topic/messages")
    public String unsubscribeUser(Message message){
        logger.info("Received a new web message : " + message);
        notificationConsumer.unsubscribe(message.getFrom());
        return "subscribed : "+message.getFrom();
    }

    public void sendMessageToTopic(String topic, String message) {
        logger.error("send to topic : "+topic+" message: "+message);
        this.messageSendingOperations.convertAndSend(topic, message);
    }
 
}