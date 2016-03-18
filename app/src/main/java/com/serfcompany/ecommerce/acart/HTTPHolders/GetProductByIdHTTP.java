package com.serfcompany.ecommerce.acart.HTTPHolders;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by serfcompany on 29.02.16.
 */
public class GetProductByIdHTTP {
    String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=single-product&id=";
    public String loadProductByID(int id) throws IOException{

            URL url = new URL(urlString+id);
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
