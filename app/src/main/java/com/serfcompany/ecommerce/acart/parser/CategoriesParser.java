package com.serfcompany.ecommerce.acart.parser;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serfcompany.ecommerce.acart.model.category.Category;
import com.serfcompany.ecommerce.acart.model.product.Example;
import com.serfcompany.ecommerce.acart.model.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serfcompany on 29.02.16.
 */
public class CategoriesParser {

    public List<Category> parseCategories(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();

        JsonParser parser = new JsonParser();
        JsonArray array = (JsonArray) parser.parse(JSON);

        List<Category> categories = new ArrayList<>();

        for (JsonElement element : array){
            Category category = new Category();
            category = gson.fromJson(element, Category.class);
            categories.add(category);
        }

        Log.i("LOG", categories.size() + " categories was parsed");
        return categories;
    }

}
