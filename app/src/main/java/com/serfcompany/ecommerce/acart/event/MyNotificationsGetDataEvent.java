package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.notification.NotificationData;

import java.util.List;

public class MyNotificationsGetDataEvent {
        
    private List<NotificationData> myNotifications;

    public MyNotificationsGetDataEvent(List<NotificationData> myNotifications){
        this.myNotifications = myNotifications;
    }

    public List<NotificationData> getMyNotifications() {
        return myNotifications;
    }

    public void clearDatas(){
        myNotifications.clear();
    }
}
