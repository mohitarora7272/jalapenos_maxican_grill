package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.orders.MyOrder;

/**
 * Created by serfcompany on 02.03.16.
 */
public class SingleOrderParser {

    public MyOrder parse(String JSON){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        MyOrder order = gson.fromJson(JSON, MyOrder.class);
        return order;
    }
}
