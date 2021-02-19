package fr.polytech.notification.components;

public interface NotificationConsumer {
     void subscribe(String supplierId);
     void unsubscribe(String supplierId);

}
