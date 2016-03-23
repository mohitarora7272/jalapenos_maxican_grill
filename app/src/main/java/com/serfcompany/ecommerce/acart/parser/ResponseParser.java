package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.response.Response;
import com.serfcompany.ecommerce.acart.model.user.Profile;

public class ResponseParser {

    public Response parse(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Response response = gson.fromJson(JSON, Response.class);
        return response;
    }

}
