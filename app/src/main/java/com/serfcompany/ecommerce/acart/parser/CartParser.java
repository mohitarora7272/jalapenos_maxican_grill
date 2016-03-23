package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.cart.Cart;

public class CartParser {

    public Cart parse(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Cart cart = gson.fromJson(JSON, Cart.class);
        return cart;
    }
}
