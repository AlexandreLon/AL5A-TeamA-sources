package fr.polytech.notification.components;

import fr.polytech.supplierregistry.components.SupplierAuthenticator;
import fr.polytech.supplierregistry.errors.SupplierNotFoundException;
import fr.polytech.supplierregistry.models.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ComponentScan({"fr.polytech.supplierregistry"})
@EntityScan({"fr.polytech.supplierregistry.models","fr.polytech.task.models"})
public class NotificationBean implements NotificationConsumer, NotificationProducer, NotificationInitializer {

    @Autowired
    SupplierAuthenticator supplierAuthenticator;

    //NotifyCallBack notifyCallBack;

    //List<Supplier> supplierSubscribed = new ArrayList<>();

    @Override
    public void subscribe(String supplierId) {
        try {
            Supplier supplier = supplierAuthenticator.getSupplierById(Long.parseLong(supplierId));
          //  supplierSubscribed.add(supplier);
        } catch (SupplierNotFoundException e) {
            // Todo custom exception
            e.printStackTrace();
        }


    }

    @Override
    public void unsubscribe(String supplierId) {
       // supplierSubscribed = supplierSubscribed.stream().filter(s -> s.getId() != Long.parseLong(supplierId)).collect(Collectors.toList());
    }

    @Override
    public void notify(List<Supplier> suppliersToNotify, String message) {
        // Call to notifyCallBack
    /*    if(notifyCallBack == null){
            throw new RuntimeException("Callback hasn't been initialized yet.");
        }
        for (Supplier supplier : suppliersToNotify) {
            if(supplierSubscribed.contains(supplier)){
                notifyCallBack.execute("/topic/supplier/"+supplier.getId().toString(),message);
            }
        }*/
    }

    @Override
    public void init(NotifyCallBack callBack) {
        //notifyCallBack = callBack;
    }
}
