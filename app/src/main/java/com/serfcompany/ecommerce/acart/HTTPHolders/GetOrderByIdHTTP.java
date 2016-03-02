package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by serfcompany on 02.03.16.
 */
public class GetOrderByIdHTTP {

    private String urlString = "http://woocommerce.serfcompany.com/?amazingcart=json-api&type=&type=get-order";

    public String getOrderById(String username, String password, String orderId) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = "&username=" + username + "&password=" + password + "&orderID=" + orderId;
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
