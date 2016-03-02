package com.serfcompany.ecommerce.acart.HTTPHolders;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

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
public class SignUpRequestHTTP {

    private static final String urlString =
            "http://woocommerce.serfcompany.com/?amazingcart=json-api&type=user-registration";

    public String userSignUp(String username, String email, String firstname,
                          String lastname, String password, String deviceID) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            String params = "&username="+username+"&email="+email+
                    "&first_name="+firstname+"&last_name="+lastname+"&password="+password+"&deviceID="+deviceID;

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
                Log.i("LOG", "line = " + line);
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
