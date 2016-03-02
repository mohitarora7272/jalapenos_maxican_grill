package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.provider.SyncStateContract;
import android.util.Log;

import com.serfcompany.ecommerce.acart.model.category.Category;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by serfcompany on 29.02.16.
 */
public class GetCategoriesHTTP {
    private static final String urlString =
            "http://woocommerce.serfcompany.com/?amazingcart=json-api&type=product-categories";

    public String loadCategories() {
        try{
            URL url = new URL(urlString);
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
