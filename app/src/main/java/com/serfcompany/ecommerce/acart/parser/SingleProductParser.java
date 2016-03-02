package com.serfcompany.ecommerce.acart.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.serfcompany.ecommerce.acart.model.product.Product;

public class SingleProductParser {

    public Product parseSingleProduct(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();
        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(JSON);
        Product product = gson.fromJson(array.get(0), Product.class);
        Log.i("LOG", product.getGeneral().getTitle() + " product was parsed");
        return product;
    }
}
