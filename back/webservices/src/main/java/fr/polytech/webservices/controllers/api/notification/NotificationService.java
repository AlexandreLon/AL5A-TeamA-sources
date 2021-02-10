package fr.polytech.webservices.controllers.api.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import fr.polytech.notification.models.Notification;
import fr.polytech.webservices.websockets.WebSocketEventListener;
 
@Controller
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
 
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public Notification sendMessage(@Payload Notification chatMessage) {
        logger.info("Received a new web message : " + chatMessage.getContent());
        return chatMessage;
    }
 
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public Notification addUser(@Payload Notification chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
 
}