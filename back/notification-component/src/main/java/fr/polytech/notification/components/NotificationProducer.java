package fr.polytech.notification.components;

import fr.polytech.supplierregistry.models.Supplier;

import java.util.List;

public interface NotificationProducer {
    void notify(List<Supplier> suppliersToNotify, String message);
}
