package com.serfcompany.ecommerce.acart.HTTPHolders;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by serfcompany on 15.03.16.
 */
public class GetChildCategories {
    private static final String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=product-categories";

    public String loadCategoriesForParent(String parentID) throws IOException {
        URL url = new URL(urlString+"&parent="+parentID);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();

    }
}
