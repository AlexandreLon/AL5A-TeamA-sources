package fr.polytech.notification.components;

public interface NotificationInitializer {

    public static interface NotifyCallBack{
        void execute(String topic, String message);
    }
    void init(NotifyCallBack callBack);


}
