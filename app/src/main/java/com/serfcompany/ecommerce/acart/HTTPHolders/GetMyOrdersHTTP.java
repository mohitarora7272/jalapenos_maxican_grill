package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetMyOrdersHTTP {

    String urlString = Constants.APP_URI +
            "?amazingcart=json-api&type=get-my-order";

    public String getMyOrders(String username, String password, String filter){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = "&username="+username+"&password="+password+"&filter="+filter;
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null){
                result.append(line);
            }
            Log.i("LOG", "Getting orders for : "+username+" with password : "+password);
            rd.close();
            Log.i("ORDER", result.toString());
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
