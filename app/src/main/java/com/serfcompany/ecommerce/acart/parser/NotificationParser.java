package com.serfcompany.ecommerce.acart.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.notification.Notification;
import com.serfcompany.ecommerce.acart.model.notification.NotificationData;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.model.orders.Orders;

import java.util.List;

public class NotificationParser {

    public List<NotificationData> parse(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();

        Notification notification = gson.fromJson(JSON, Notification.class);
        List<NotificationData> myNotifications = notification.getData();
        Log.i("LOG", myNotifications.size() + " notifications was parsed");
        return myNotifications;
    }
}
