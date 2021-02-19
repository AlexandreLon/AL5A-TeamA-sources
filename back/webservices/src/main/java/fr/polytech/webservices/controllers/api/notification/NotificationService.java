package fr.polytech.webservices.controllers.api.notification;

import fr.polytech.notification.components.NotificationConsumer;
import fr.polytech.notification.components.NotificationInitializer;
import fr.polytech.webservices.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import fr.polytech.notification.models.Notification;
import fr.polytech.webservices.websockets.WebSocketEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

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
    public String registerUser(Message message) throws Exception{
        logger.info("Received a new web message : " + message);
        notificationConsumer.subscribe(message.getFrom());
        return "subscribed : "+message.getFrom();
    }

    @MessageMapping("/unsubscribe")
    @SendTo("/topic/messages")
    public String unsubscribeUser(Message message) throws Exception{
        logger.info("Received a new web message : " + message);
        notificationConsumer.unsubscribe(message.getFrom());
        return "subscribed : "+message.getFrom();
    }

   /* @Scheduled(fixedDelay = 5000)
    public void sendPeriodicMessages() {
        logger.info("send...");
        String broadcast = String.format("server periodic message %s via the broker", LocalTime.now());
        this.messageSendingOperations.convertAndSend("/topic/messages", broadcast);
    }*/

    public void sendMessageToTopic(String topic, String message) {
        logger.error("send to topic : "+topic+" message: "+message);
        this.messageSendingOperations.convertAndSend(topic, message);
    }
 
}