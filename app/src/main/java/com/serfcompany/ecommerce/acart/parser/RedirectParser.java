package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.response.Redirect;

/**
 * Created by serfcompany on 14.03.16.
 */
public class RedirectParser {

    public Redirect parse(String JSON){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Redirect redirect = gson.fromJson(JSON, Redirect.class);
        return redirect;
    }

}
