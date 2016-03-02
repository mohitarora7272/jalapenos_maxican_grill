package com.serfcompany.ecommerce.acart.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by serfcompany on 02.03.16.
 */
public class SignUpCodeParser {

    public int parseStatus(String JSON){
        JSONObject object = null;
        try {
            object = new JSONObject(JSON);
            return object.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 4;
    }
}
