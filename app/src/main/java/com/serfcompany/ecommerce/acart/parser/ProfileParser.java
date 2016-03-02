package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.user.Profile;

/**
 * Created by serfcompany on 01.03.16.
 */
public class ProfileParser {


    public Profile parseProfile(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();
        Profile profile = gson.fromJson(JSON, Profile.class);
        return profile;
    }
}
