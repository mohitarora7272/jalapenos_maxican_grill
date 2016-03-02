package com.serfcompany.ecommerce.acart.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.product.Example;
import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductsParser {

    public List<Product> parseProducts(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();
        Example example = gson.fromJson(JSON, Example.class);
        List<Product> products = example.getProducts();
        Log.i("LOG", products.size()+ " products was parsed");
        return products;
    }
}
