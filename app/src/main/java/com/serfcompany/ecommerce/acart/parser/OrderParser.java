package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

/**
 * Created by serfcompany on 14.03.16.
 */
public class OrderParser {

    public MyOrder parse(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();

        MyOrder order = gson.fromJson(JSON, MyOrder.class);
        return order;
    }
}
