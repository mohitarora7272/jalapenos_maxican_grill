package com.serfcompany.ecommerce.acart.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;
import com.serfcompany.ecommerce.acart.model.orders.Orders;

import java.util.List;

/**
 * Created by serfcompany on 02.03.16.
 */
public class OrdersParser {

    public List<MyOrder> parseOrders(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();

        Orders orders = gson.fromJson(JSON, Orders.class);
        List<MyOrder> myOrders = orders.getMyOrder();
        Log.i("LOG", myOrders.size() + " orders was parsed");
        return myOrders;
    }
}
