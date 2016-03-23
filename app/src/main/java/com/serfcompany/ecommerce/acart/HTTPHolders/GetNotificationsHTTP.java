package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import com.serfcompany.ecommerce.acart.Constants;
import com.serfcompany.ecommerce.acart.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNotificationsHTTP {

    String urlString = Constants.APP_URI +
            "?amazingcart=notification&type=json";

    public String loadNotifications(String page, String postPerPage, String username, String password){
        try {
            URL url = new URL(urlString+"&page="+page+"&postPerPage="+postPerPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String params = "&username="+username+"&password="+password;

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
            rd.close();

            Log.i("LOG", "Notifications was parsed = " + result);
            return result.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
